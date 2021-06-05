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
public class MelonManglerRobesModel extends CosmeticModel {
    private final ModelPart robes;
    private final ModelPart leftArm;
    private final ModelPart rightArm;

    public MelonManglerRobesModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        robes = new ModelPart(this);
        robes.setTextureOffset(1, 42).addCuboid(-4.0F, 0.0F, -2.5F, 8.0F, 18.0F, 5.0F, 0.6F, false);

        leftArm = new ModelPart(this);
        leftArm.setPivot(-6.0F, 2.0F, 0.0F);
        leftArm.setTextureOffset(30, 42).addCuboid(-2.75F, -1.75F, -2.0F, 4.0F, 7.0F, 4.0F, 0.28F, false);

        rightArm = new ModelPart(this);
        rightArm.setPivot(6.0F, 2.0F, 0.0F);
        rightArm.setTextureOffset(30, 42).addCuboid(-1.25F, -1.75F, -2.0F, 4.0F, 7.0F, 4.0F, 0.28F, true);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        PlayerEntityModel<AbstractClientPlayerEntity> model = this.renderer.getModel();
        this.robes.copyPositionAndRotation(model.torso);
        this.rightArm.copyPositionAndRotation(model.leftArm);
        this.leftArm.copyPositionAndRotation(model.rightArm);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.leftArm, this.rightArm, this.robes);
    }
}
