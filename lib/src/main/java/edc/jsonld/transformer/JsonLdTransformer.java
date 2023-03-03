package edc.jsonld.transformer;

import edc.transformer.TypeTransformer;

/**
 * Base type for transformers that operate on JSON-LD types. JSON-LD types (input and output) must be expanded per the JSON-LD Processing Algorithms API.
 * <p>
 * {@see https://www.w3.org/TR/json-ld11-api/}
 */
public interface JsonLdTransformer<INPUT, OUTPUT> extends TypeTransformer<INPUT, OUTPUT> {


}
