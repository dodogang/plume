package net.dodogang.plume.donor.client.cosmetic.model.vagabond;

import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.dodogang.plume.donor.client.cosmetic.model.CapeCosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.util.Identifier;

public class VagabondCapeModel extends CapeCosmeticModel {
    private final ModelPart cape;

    public VagabondCapeModel(CapeFeatureRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        cape = new ModelPart(this);
        cape.setTextureOffset(40, 12).addCuboid(-7.0F, 0.0F, -1.1F, 14.0F, 18.0F, 1.0F, 0.0F, false);
    }

    @Override
    public ModelPart getCape() {
        return this.cape;
    }

    @Override
    public Identifier getTexture() {
        return CosmeticsClient.Texture.VAGABOND;
    }
}
