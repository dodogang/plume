package net.dodogang.plume.donor.client.cosmetic.model.melon_mangler;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class MelonManglerBootsModel extends CosmeticModel {
    private final ModelPart leftBoot;
    private final ModelPart rightBoot;

    public MelonManglerBootsModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        leftBoot = new ModelPart(this);
        leftBoot.setPivot(0.0F, 24.0F, 0.0F);
        leftBoot.setTextureOffset(46, 42).addCuboid(-2.0F, 5.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.5F, true);
        leftBoot.setTextureOffset(62, 42).addCuboid(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.3F, false);
        leftBoot.setTextureOffset(65, 48).addCuboid(-2.0F, 10.0F, -3.5F, 4.0F, 2.0F, 1.0F, 0.3F, true);

        rightBoot = new ModelPart(this);
        rightBoot.setPivot(0.0F, 24.0F, 0.0F);
        rightBoot.setTextureOffset(46, 42).addCuboid(-2.0F, 5.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.5F, false);
        rightBoot.setTextureOffset(62, 42).addCuboid(-2.0F, 7.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.3F, false);
        rightBoot.setTextureOffset(65, 48).addCuboid(-2.0F, 10.0F, -3.5F, 4.0F, 2.0F, 1.0F, 0.3F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        PlayerEntityModel<AbstractClientPlayerEntity> model = this.renderer.getModel();
        this.leftBoot.copyPositionAndRotation(model.leftLeg);
        this.rightBoot.copyPositionAndRotation(model.rightLeg);
        this.rightBoot.visible = true;
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.leftBoot, this.rightBoot);
    }
}
