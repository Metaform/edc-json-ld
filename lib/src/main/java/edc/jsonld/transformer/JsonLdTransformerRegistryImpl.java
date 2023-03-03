/*
 *  Copyright (c) 2020 - 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - improvements
 *
 */

package edc.jsonld.transformer;

import edc.EdcException;
import edc.result.Result;
import edc.transformer.TransformerContext;
import edc.transformer.TransformerContextImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;


public class JsonLdTransformerRegistryImpl implements JsonLdTransformerRegistry {

    private final Set<JsonLdTransformer<?, ?>> transformers;

    public JsonLdTransformerRegistryImpl() {
        transformers = new HashSet<>();
    }

    public Set<JsonLdTransformer<?, ?>> getTransformers() {
        return Collections.unmodifiableSet(transformers);
    }

    @Override
    public void register(JsonLdTransformer<?, ?> transformer) {
        transformers.add(transformer);
    }

    @Override
    public <INPUT, OUTPUT> Result<OUTPUT> transform(@NotNull INPUT object, @NotNull Class<OUTPUT> outputType) {

        var ctx = new TransformerContextImpl(this);

        var output = transform(object, outputType, ctx);

        return ctx.hasProblems() ? Result.failure(ctx.getProblems()) : Result.success(output);
    }

    private <INPUT, OUTPUT> OUTPUT transform(INPUT object, Class<OUTPUT> outputType, TransformerContext context) {
        requireNonNull(object);

        var t = transformers.stream()
                .filter(tr -> tr.getInputType().isInstance(object) && tr.getOutputType().equals(outputType))
                .findFirst().orElse(null);

        if (t == null) {
            throw new EdcException(format("No transformer registered that can handle %s -> %s", object, outputType));
        }
        //noinspection unchecked
        return outputType.cast(((JsonLdTransformer<INPUT, OUTPUT>) t).transform(object, context));
    }

}
