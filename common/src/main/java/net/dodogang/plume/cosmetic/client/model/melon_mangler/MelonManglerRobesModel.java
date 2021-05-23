package net.dodogang.plume.cosmetic.client.model.melon_mangler;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.cosmetic.client.model.CosmeticModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class MelonManglerRobesModel extends CosmeticModel {
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart robes;

    public MelonManglerRobesModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        leftArm = new ModelPart(this);
        leftArm.setTextureOffset(30, 94).addCuboid(-1.5F, -1.75F, -2.0F, 4.0F, 7.0F, 4.0F, 0.28F, false);

        rightArm = new ModelPart(this);
        rightArm.setTextureOffset(30, 94).addCuboid(-2.5F, -1.75F, -2.0F, 4.0F, 7.0F, 4.0F, 0.28F, true);

        robes = new ModelPart(this);
        robes.setTextureOffset(81, 102).addCuboid(-4.0F, 0.0F, -2.5F, 8.0F, 18.0F, 5.0F, 0.6F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        PlayerEntityModel<AbstractClientPlayerEntity> model = this.renderer.getModel();
        this.leftArm.copyPositionAndRotation(model.leftArm);
        this.rightArm.copyPositionAndRotation(model.rightArm);
        this.robes.copyPositionAndRotation(model.torso);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.rightArm, this.leftArm, this.robes);
    }

    public static void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }
}
