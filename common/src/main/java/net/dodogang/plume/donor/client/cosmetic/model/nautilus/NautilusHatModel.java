package net.dodogang.plume.donor.client.cosmetic.model.nautilus;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

public class NautilusHatModel extends CosmeticModel {
    private final ModelPart hat;

    public NautilusHatModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        hat = new ModelPart(this);
        hat.setTextureOffset(0, 0).addCuboid(-5.0F, -14.0F, 1.0F, 10.0F, 12.0F, 8.0F, 0.0F, false);
        hat.setTextureOffset(0, 20).addCuboid(-5.0F, -14.0F, -5.0F, 10.0F, 5.0F, 6.0F, 0.0F, false);
        hat.setTextureOffset(0, 31).addCuboid(-6.0F, -9.0F, -7.0F, 12.0F, 3.0F, 8.0F, 0.0F, false);
    }
    @Override
    public void setAngles(PlayerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.hat.copyPositionAndRotation(this.renderer.getModel().getHead());
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.hat);
    }
}
