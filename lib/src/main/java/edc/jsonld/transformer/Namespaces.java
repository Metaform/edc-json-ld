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

package edc.jsonld.transformer;

/**
 * Well-known schema namespace definitions.
 */
public interface Namespaces {

    String DCAT_PREFIX = "dcat";
    String DCAT_SCHEMA = "https://www.w3.org/ns/dcat/";

    String ODRL_PREFIX = "odrl";
    String ODRL_SCHEMA = "https://www.w3.org/TR/odrl-model/";

    String DCT_PREFIX = "dct";
    String DCT_SCHEMA = "https://purl.org/dc/terms/";

}
