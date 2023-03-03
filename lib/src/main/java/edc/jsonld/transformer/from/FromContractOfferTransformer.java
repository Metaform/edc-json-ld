package edc.jsonld.transformer.from;

import edc.jsonld.transformer.AbstractJsonLdTransformer;
import edc.model.asset.Asset;
import edc.model.contract.ContractOffer;
import edc.transformer.TransformerContext;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static edc.jsonld.transformer.JsonLdKeywords.ID;
import static edc.jsonld.transformer.JsonLdKeywords.TYPE;
import static edc.jsonld.transformer.Namespaces.DCAT_SCHEMA;

/**
 * Converts a {@link ContractOffer} to a DCAT Dataset as a {@link JsonObject} in JSON-LD expanded form.
 */
public class FromContractOfferTransformer extends AbstractJsonLdTransformer<ContractOffer, JsonObject> {
    private final JsonBuilderFactory jsonFactory;

    public FromContractOfferTransformer(JsonBuilderFactory jsonFactory) {
        super(ContractOffer.class, JsonObject.class);
        this.jsonFactory = jsonFactory;
    }

    @Override
    public @Nullable JsonObject transform(@Nullable ContractOffer contractOffer, @NotNull TransformerContext context) {
        if (contractOffer == null) {
            return null;
        }

        var datasetBuilder = jsonFactory.createObjectBuilder();
        datasetBuilder.add(ID, contractOffer.getId());
        datasetBuilder.add(TYPE, DCAT_SCHEMA + "Datset");

        transformPolicy(contractOffer, datasetBuilder, context);

        addAssetData(contractOffer.getAsset(), datasetBuilder, context);

        return datasetBuilder.build();
    }

    private void addAssetData(Asset asset, JsonObjectBuilder datasetBuilder, TransformerContext context) {
    }

    private void transformPolicy(ContractOffer contractOffer, JsonObjectBuilder datasetBuilder, TransformerContext context) {
        var policy = contractOffer.getPolicy();
        var policyObject = context.transform(policy, JsonObject.class);
    }
}
