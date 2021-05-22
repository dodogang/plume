package net.dodogang.plume.cosmetic.client.model.melon_mangler;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.cosmetic.client.model.CosmeticModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class MelonManglerRobesModel extends CosmeticModel {
    private final ModelPart robes;

    public MelonManglerRobesModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        robes = new ModelPart(this);
        robes.setPivot(0.0F, 0.0F, 0.0F);
        robes.setTextureOffset(81, 102).addCuboid(-4.0F, 0.0F, -2.5F, 8.0F, 18.0F, 5.0F, 0.6F);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.robes.copyPositionAndRotation(this.renderer.getModel().torso);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.robes);
    }
}
