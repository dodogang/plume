package net.dodogang.plume.cosmetic.client.model.nautilus;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.cosmetic.client.model.CosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

public class YellowNautilusHatModel extends CosmeticModel {
    private final ModelPart hat;

    public YellowNautilusHatModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        hat = new ModelPart(this);
        hat.setTextureOffset(0, 23).addCuboid(-6.0F, -9.5F, -8.0F, 12.0F, 4.0F, 12.0F, 0.0F, false);
        hat.setTextureOffset(0, 9).addCuboid(-4.0F, -15.5F, -5.0F, 8.0F, 6.0F, 8.0F, 0.0F, false);
        hat.setTextureOffset(0, 0).addCuboid(-2.0F, -20.5F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        this.hat.copyPositionAndRotation(this.renderer.getModel().getHead());
        setRotationAngle(this.hat, this.hat.pitch + -0.3054F, this.hat.yaw, this.hat.roll);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.hat);
    }
}
