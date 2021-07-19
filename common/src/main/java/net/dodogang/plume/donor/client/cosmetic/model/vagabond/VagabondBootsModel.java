package net.dodogang.plume.donor.client.cosmetic.model.vagabond;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerEntity;

public class VagabondBootsModel extends CosmeticModel {
    private final ModelPart leftBoot;
    private final ModelPart rightBoot;

    public VagabondBootsModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        leftBoot = new ModelPart(this);
        leftBoot.setPivot(0.0F, 24.0F, 0.0F);
        leftBoot.setTextureOffset(90, 24).addCuboid(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.3F, false);
        leftBoot.setTextureOffset(93, 28).addCuboid(-2.0F, 10.0F, -3.6F, 4.0F, 2.0F, 1.0F, 0.3F, false);
        leftBoot.setTextureOffset(70, 24).addCuboid(-2.5F, 5.6F, -2.5F, 5.0F, 4.0F, 5.0F, 0.3F, false);

        rightBoot = new ModelPart(this);
        rightBoot.setPivot(0.0F, 24.0F, 0.0F);
        rightBoot.setTextureOffset(90, 24).addCuboid(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.3F, true);
        rightBoot.setTextureOffset(93, 28).addCuboid(-2.0F, 10.0F, -3.6F, 4.0F, 2.0F, 1.0F, 0.3F, true);
        rightBoot.setTextureOffset(70, 24).addCuboid(-2.5F, 5.6F, -2.5F, 5.0F, 4.0F, 5.0F, 0.3F, true);
    }

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        PlayerEntityModel<AbstractClientPlayerEntity> model = this.renderer.getModel();
        this.leftBoot.copyPositionAndRotation(model.leftLeg);
        this.rightBoot.copyPositionAndRotation(model.rightLeg);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.leftBoot, this.rightBoot);
    }
}
