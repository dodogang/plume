package net.dodogang.plume.donor.client.cosmetic.model.melon_mangler;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
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
        mustache.setTextureOffset(60, 20).addCuboid(-7.0F, -3.0F, -4.52F, 14.0F, 2.0F, 0.0F, 0.0F, false);
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
