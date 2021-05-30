package net.dodogang.plume.donor.client.cosmetic.model.vagabond;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

public class VagabondHoodModel extends CosmeticModel {
    private final ModelPart hat;
    private final ModelPart tail;

    public VagabondHoodModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        hat = new ModelPart(this);
        hat.setTextureOffset(0, 0).addCuboid(-5.0F, -9.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPivot(0.0F, -5.75F, 4.0F);
        hat.addChild(this.tail);
        tail.setTextureOffset(40, 0).addCuboid(-3.0F, -1.5F, 0.5F, 6.0F, 7.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        this.hat.copyPositionAndRotation(this.renderer.getModel().getHead());
        setRotationAngle(this.tail, this.hat.pitch + -0.2618F,  this.tail.yaw, this.tail.roll);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.hat);
    }
}
