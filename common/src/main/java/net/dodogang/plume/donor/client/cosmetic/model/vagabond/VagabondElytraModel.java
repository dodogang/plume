package net.dodogang.plume.donor.client.cosmetic.model.vagabond;

import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.dodogang.plume.donor.client.cosmetic.model.ElytraCosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;

public class VagabondElytraModel extends ElytraCosmeticModel {
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public VagabondElytraModel() {
        super();

        this.textureWidth = 128;
        this.textureHeight = 128;

        leftWing = new ModelPart(this);
        leftWing.setTextureOffset(86, 96).addCuboid(-3.75F, -1.0F, -1.0F, 10.0F, 20.0F, 2.0F, 0.0F, false);

        rightWing = new ModelPart(this);
        rightWing.setTextureOffset(86, 96).addCuboid(-6.25F, -1.0F, -1.0F, 10.0F, 20.0F, 2.0F, 0.0F, true);
    }

    @Override
    public ModelPart getLeftWing() {
        return this.leftWing;
    }
    @Override
    public ModelPart getRightWing() {
        return this.rightWing;
    }

    @Override
    public Identifier getTexture() {
        return CosmeticsClient.Texture.VAGABOND;
    }
}
