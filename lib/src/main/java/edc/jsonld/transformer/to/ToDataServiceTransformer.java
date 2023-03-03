package edc.jsonld.transformer.to;

import edc.jsonld.model.DataService;
import edc.jsonld.transformer.AbstractJsonLdTransformer;
import edc.transformer.TransformerContext;
import jakarta.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 */
public class ToDataServiceTransformer extends AbstractJsonLdTransformer<JsonObject, DataService> {

    public ToDataServiceTransformer() {
        super(JsonObject.class, DataService.class);
    }

    @Override
    public @Nullable DataService transform(@Nullable JsonObject object, @NotNull TransformerContext context) {
        return null;
    }
}
