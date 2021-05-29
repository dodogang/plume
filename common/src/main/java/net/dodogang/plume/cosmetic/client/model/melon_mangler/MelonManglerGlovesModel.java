package net.dodogang.plume.cosmetic.client.model.melon_mangler;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.cosmetic.client.model.CosmeticModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class MelonManglerGlovesModel extends CosmeticModel {
    private final ModelPart leftGlove;
    private final ModelPart leftCuff;
    private final ModelPart rightGlove;
    private final ModelPart rightCuff;

    public MelonManglerGlovesModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        leftGlove = new ModelPart(this);
        leftGlove.setTextureOffset(60, 11).addCuboid(-1.5F, 5.5F, -2.0F, 4.0F, 5.0F, 4.0F, 0.3F, false);

        leftCuff = new ModelPart(this);
        leftGlove.addChild(leftCuff);
        leftCuff.setTextureOffset(64, 11).addCuboid(-4.0F, 2.7F, -2.0F, 2.0F, 0.952380952381F, 4.0F, 0.4F, false);

        rightGlove = new ModelPart(this);
        rightGlove.setTextureOffset(60, 11).addCuboid(-2.5F, 5.5F, -2.0F, 4.0F, 5.0F, 4.0F, 0.3F, true);

        rightCuff = new ModelPart(this);
        rightGlove.addChild(rightCuff);
        rightCuff.setTextureOffset(64, 11).addCuboid(2.0F, 2.7F, -2.0F, 2.0F, 1.0F, 4.0F, 0.4F, true);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        PlayerEntityModel<AbstractClientPlayerEntity> model = this.renderer.getModel();
        this.leftGlove.copyPositionAndRotation(model.leftArm);
        this.rightGlove.copyPositionAndRotation(model.rightArm);

        this.leftCuff.copyPositionAndRotation(this.leftGlove);
        this.rightCuff.copyPositionAndRotation(this.rightGlove);
        setRotationAngle(this.leftCuff, this.leftCuff.pitch, this.leftCuff.yaw, this.leftCuff.roll + -0.2618F);
        setRotationAngle(this.rightCuff, this.rightCuff.pitch, this.rightCuff.yaw, this.rightCuff.roll + 0.2618F);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.leftGlove, this.rightGlove);
    }
}
