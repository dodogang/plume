package net.dodogang.plume.donor.client.cosmetic.model.vagabond;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.dodogang.plume.donor.client.cosmetic.model.CapeCosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;

public class VagabondCapeModel extends CapeCosmeticModel {
    private final ModelPart cape;

    public VagabondCapeModel() {
        super();

        this.textureWidth = 128;
        this.textureHeight = 128;

        cape = new ModelPart(this);
        cape.setPivot(0.0F, 0.0F, 2.0F);
        cape.setTextureOffset(40, 12).addCuboid(-7.0F, -1.0F, -3.0F, 14.0F, 18.0F, 1.0F, 0.0F, false);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.cape);
    }

    @Override
    public Identifier getTexture() {
        return CosmeticsClient.Texture.VAGABOND;
    }
}
