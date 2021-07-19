package net.dodogang.plume.donor.client.cosmetic.model.vagabond;

import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.dodogang.plume.donor.client.cosmetic.model.CloakCosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.util.Identifier;

public class VagabondCloakModel extends CloakCosmeticModel {
    private final ModelPart cloak;

    public VagabondCloakModel(CapeFeatureRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        cloak = new ModelPart(this);
        cloak.setTextureOffset(40, 12).addCuboid(-7.0F, 0.0F, -1.1F, 14.0F, 18.0F, 1.0F, 0.0F, false);
    }

    @Override
    public ModelPart getCloak() {
        return this.cloak;
    }

    @Override
    public Identifier getTexture() {
        return CosmeticsClient.Texture.VAGABOND;
    }
}
