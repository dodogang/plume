package net.dodogang.plume.cosmetic.client.model.melon_mangler;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.cosmetic.client.model.CosmeticModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class MelonManglerMoustacheModel extends CosmeticModel {
    private final ModelPart mustache;

    public MelonManglerMoustacheModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        mustache = new ModelPart(this);
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
