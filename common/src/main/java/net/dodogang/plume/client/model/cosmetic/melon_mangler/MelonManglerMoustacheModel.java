package net.dodogang.plume.client.model.cosmetic.melon_mangler;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.client.model.cosmetic.CosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

public class MelonManglerMoustacheModel extends CosmeticModel {
    private final ModelPart mustache;

    public MelonManglerMoustacheModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        mustache = new ModelPart(this);
        mustache.setPivot(0.0F, 23.0F, -0.25F);
        mustache.setTextureOffset(100, 126).addCuboid(-7.0F, -3.0F, -4.2F, 14.0F, 2.0F, 0.0F);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.mustache.copyPositionAndRotation(this.renderer.getModel().getHead());
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.mustache);
    }
}
