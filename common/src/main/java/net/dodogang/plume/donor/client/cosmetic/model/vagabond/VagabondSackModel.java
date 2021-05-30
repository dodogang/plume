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
    private final ModelPart sack;

    public VagabondSackModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        pauldrons = new ModelPart(this);
        pauldrons.setTextureOffset(0, 20).addCuboid(-4.0F, -1.0F, -2.0F, 8.0F, 11.0F, 4.0F, 0.4F, false);
        pauldrons.setTextureOffset(24, 20).addCuboid(4.75F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.4F, true);
        pauldrons.setTextureOffset(24, 20).addCuboid(-8.75F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.4F, false);

        sack = new ModelPart(this);
        sack.setTextureOffset(70, 0).addCuboid(-6.0F, 0.0F, 4.5F, 12.0F, 12.0F, 12.0F, 0.0F, false);
        sack.setTextureOffset(106, 5).addCuboid(-2.0F, -3.0F, 8.5F, 4.0F, 3.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        PlayerEntityModel<AbstractClientPlayerEntity> model = this.renderer.getModel();
        ItemStack chest = entity.getEquippedStack(EquipmentSlot.CHEST);

        this.pauldrons.copyPositionAndRotation(model.torso);
        this.pauldrons.visible = chest.isEmpty();

        this.sack.copyPositionAndRotation(model.torso);
        setRotationAngle(this.sack, this.sack.pitch + -0.2618F, this.sack.yaw, this.sack.roll);
        this.sack.visible = !(DonorDataManager.get(entity.getUuid()).getConfig(DonorData.ConfigOptions.BOOL_RENDER_CAPES_AND_ELYTRAS).getAsBoolean() || (entity.isPartVisible(PlayerModelPart.CAPE) && (entity instanceof AbstractClientPlayerEntity && ((AbstractClientPlayerEntity) entity).getCapeTexture() != null))) || chest.getItem() == Items.ELYTRA;
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.pauldrons, this.sack);
    }
}
