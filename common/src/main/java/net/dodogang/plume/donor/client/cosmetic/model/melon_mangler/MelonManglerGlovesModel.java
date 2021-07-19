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
public class MelonManglerGlovesModel extends CosmeticModel {
    private final ModelPart leftGlove;
    private final ModelPart rightGlove;

    public MelonManglerGlovesModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        rightGlove = new ModelPart(this);
        rightGlove.setTextureOffset(60, 11)
                  .addCuboid(-3.0F, 3.75F, -2.0F, 4.0F, 4.0F, 4.0F, 0.375F, false);

        leftGlove = new ModelPart(this);
        leftGlove.setTextureOffset(60, 11)
                 .addCuboid(-1.0F, 3.75F, -2.0F, 4.0F, 4.0F, 4.0F, 0.375F, true);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        PlayerEntityModel<AbstractClientPlayerEntity> model = this.renderer.getModel();
        this.leftGlove.copyPositionAndRotation(model.leftArm);
        this.rightGlove.copyPositionAndRotation(model.rightArm);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.leftGlove, this.rightGlove);
    }
}
