package net.dodogang.plume.cosmetic.client.model.nautilus;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.cosmetic.client.model.CosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

public class OrangeNautilusBackpackModel extends CosmeticModel {
    private final ModelPart backpack;

    public OrangeNautilusBackpackModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        backpack = new ModelPart(this);
        backpack.setTextureOffset(46, 0).addCuboid(-5.0F, -3.0F, 0.0F, 7.0F, 8.0F, 7.0F, 0.0F, false);
        backpack.setTextureOffset(46, 15).addCuboid(-4.0F, 5.0F, 1.0F, 5.0F, 3.0F, 5.0F, 0.0F, false);
        backpack.setTextureOffset(46, 23).addCuboid(-3.0F, 8.0F, 2.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        this.backpack.copyPositionAndRotation(this.renderer.getModel().torso);
        setRotationAngle(backpack, this.backpack.pitch, this.backpack.yaw, this.backpack.roll + -0.6109F);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.backpack);
    }
}
