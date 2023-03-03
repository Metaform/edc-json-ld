/*
 *  Copyright (c) 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

package edc.jsonld.transformer.from;

import com.fasterxml.jackson.databind.ObjectMapper;
import edc.jsonld.model.Catalog;
import edc.jsonld.transformer.AbstractJsonLdTransformer;
import edc.transformer.TransformerContext;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static edc.jsonld.transformer.Namespaces.DCAT_SCHEMA;

/**
 * Converts from a {@link Catalog} to a DCAT catalog as a {@link JsonObject} in JSON-LD expanded form.
 */
public class FromCatalogTransformer extends AbstractJsonLdTransformer<Catalog, JsonObject> {
    private final JsonBuilderFactory jsonFactory;
    private final ObjectMapper mapper;

    public FromCatalogTransformer(JsonBuilderFactory jsonFactory, ObjectMapper mapper) {
        super(Catalog.class, JsonObject.class);
        this.jsonFactory = jsonFactory;
        this.mapper = mapper;
    }

    @Override
    public @Nullable JsonObject transform(@Nullable Catalog catalog, @NotNull TransformerContext context) {
        if (catalog == null) {
            return null;
        }

        var objectBuilder = jsonFactory.createObjectBuilder();

        var datasets = catalog.getDatasets().stream()
                .map(offer -> context.transform(offer, JsonObject.class))
                .collect(jsonFactory::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add)
                .build();

        objectBuilder.add(DCAT_SCHEMA + "dataset", datasets);

        // transform properties, which are generic JSON values.
        catalog.getProperties().forEach((k, v) -> objectBuilder.add(k, mapper.convertValue(v, JsonValue.class)));

        return objectBuilder.build();
    }
}
