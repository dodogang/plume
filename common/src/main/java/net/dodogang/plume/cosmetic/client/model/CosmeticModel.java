package net.dodogang.plume.cosmetic.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public abstract class CosmeticModel extends CompositeEntityModel<PlayerEntity> {
    protected final PlayerEntityRenderer renderer;

    public CosmeticModel(PlayerEntityRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int i, int overlay, float f, float g, float h, float k) {
        super.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, f, g, h, k);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }
}
