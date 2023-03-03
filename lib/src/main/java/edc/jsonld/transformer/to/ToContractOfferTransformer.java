package edc.jsonld.transformer.to;

import edc.jsonld.transformer.AbstractJsonLdTransformer;
import edc.model.contract.ContractOffer;
import edc.transformer.TransformerContext;
import jakarta.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 */
public class ToContractOfferTransformer extends AbstractJsonLdTransformer<JsonObject, ContractOffer> {

    public ToContractOfferTransformer() {
        super(JsonObject.class, ContractOffer.class);
    }

    @Override
    public @Nullable ContractOffer transform(@Nullable JsonObject object, @NotNull TransformerContext context) {
        return null;
    }
}
