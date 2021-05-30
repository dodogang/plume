package net.dodogang.plume.donor.client.cosmetic.model.vagabond;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class VagabondSackModel extends CosmeticModel {
    private final ModelPart pauldrons;
    private final ModelPart leftPauldron;
    private final ModelPart rightPauldron;
    private final ModelPart sack;

    public VagabondSackModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        pauldrons = new ModelPart(this);
        pauldrons.setTextureOffset(0, 20).addCuboid(-4.0F, -1.0F, -2.0F, 8.0F, 11.0F, 4.0F, 0.6F, false);

        leftPauldron = new ModelPart(this);
        pauldrons.addChild(leftPauldron);
        leftPauldron.setTextureOffset(24, 20).addCuboid(4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.6F, true);

        rightPauldron = new ModelPart(this);
        pauldrons.addChild(rightPauldron);
        rightPauldron.setTextureOffset(24, 20).addCuboid(-8.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.6F, false);

        sack = new ModelPart(this);
        sack.setTextureOffset(70, 0).addCuboid(-6.0F, 0.0F, 4.5F, 12.0F, 12.0F, 12.0F, 0.0F, false);
        sack.setTextureOffset(106, 5).addCuboid(-2.0F, -3.0F, 8.5F, 4.0F, 3.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        PlayerEntityModel<AbstractClientPlayerEntity> model = this.renderer.getModel();
        ItemStack chest = entity.getEquippedStack(EquipmentSlot.CHEST);
        boolean hasElytra = chest.getItem() == Items.ELYTRA;

        this.pauldrons.copyPositionAndRotation(model.torso);
        this.pauldrons.visible = chest.isEmpty() || hasElytra;

        this.leftPauldron.copyPositionAndRotation(this.pauldrons);
        this.rightPauldron.copyPositionAndRotation(this.pauldrons);
        this.leftPauldron.pitch = model.leftArm.pitch;
        this.leftPauldron.yaw = model.leftArm.yaw;
        this.leftPauldron.roll = model.leftArm.roll;
        this.rightPauldron.pitch = model.rightArm.pitch;
        this.rightPauldron.yaw = model.rightArm.yaw;
        this.rightPauldron.roll = model.rightArm.roll;
        if (entity.isInSneakingPose()) {
            this.leftPauldron.pivotY -= 2.5F;
            this.rightPauldron.pivotY -= 2.5F;
        } else {
            this.leftPauldron.pivotY += 1.0F;
            this.rightPauldron.pivotY += 1.0F;
        }

        this.sack.copyPositionAndRotation(model.torso);
        setRotationAngle(this.sack, this.sack.pitch + -0.2618F, this.sack.yaw, this.sack.roll);
        this.sack.visible = !((DonorDataManager.get(entity.getUuid()).getConfig(DonorData.ConfigOptions.BOOL_RENDER_CAPES_AND_ELYTRAS).getAsBoolean() || entity.isPartVisible(PlayerModelPart.CAPE)) && (entity instanceof AbstractClientPlayerEntity && ((AbstractClientPlayerEntity) entity).getCapeTexture() != null)) || hasElytra;
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.pauldrons, this.sack);
    }
}
