package net.dodogang.plume.donor.client.cosmetic.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public abstract class ElytraCosmeticModel extends CosmeticModel {
    public ElytraCosmeticModel() {
        super(null);
    }

    /**
     * Copied directly from {@link ElytraEntityModel#setAngles(LivingEntity, float, float, float, float, float)}.
     */
    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        ModelPart left = this.getLeftWing();
        ModelPart right = this.getRightWing();

        float pitch = 0.2617994F;
        float roll = -0.2617994F;
        float pivotY = 0.0F;
        float yaw = 0.0F;
        if (entity.isFallFlying()) {
            float rotMod = 1.0F;
            Vec3d vec3d = entity.getVelocity();
            if (vec3d.y < 0.0D) {
                Vec3d vec3d2 = vec3d.normalize();
                rotMod = 1.0F - (float)Math.pow(-vec3d2.y, 1.5D);
            }

            pitch = rotMod * 0.34906584F + (1.0F - rotMod) * pitch;
            roll = rotMod * -1.5707964F + (1.0F - rotMod) * roll;
        } else if (entity.isInSneakingPose()) {
            pitch = 0.6981317F;
            roll = -0.7853982F;
            pivotY = 3.0F;
            yaw = 0.08726646F;
        }

        right.pivotX = 5.0F;
        right.pivotY = pivotY;
        if (entity instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity playerEntity = (AbstractClientPlayerEntity)entity;
            playerEntity.elytraPitch = (float)((double)playerEntity.elytraPitch + (double)(pitch - playerEntity.elytraPitch) * 0.1D);
            playerEntity.elytraYaw = (float)((double)playerEntity.elytraYaw + (double)(yaw - playerEntity.elytraYaw) * 0.1D);
            playerEntity.elytraRoll = (float)((double)playerEntity.elytraRoll + (double)(roll - playerEntity.elytraRoll) * 0.1D);
            right.pitch = playerEntity.elytraPitch;
            right.yaw = playerEntity.elytraYaw;
            right.roll = playerEntity.elytraRoll;
        } else {
            right.pitch = pitch;
            right.roll = roll;
            right.yaw = yaw;
        }

        left.pivotX = -right.pivotX;
        left.yaw = -right.yaw;
        left.pivotY = right.pivotY;
        left.pitch = right.pitch;
        left.roll = -right.roll;
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.getLeftWing(), this.getRightWing());
    }

    public abstract ModelPart getLeftWing();
    public abstract ModelPart getRightWing();

    public abstract Identifier getTexture();
}
