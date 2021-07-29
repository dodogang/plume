package net.dodogang.plume.donor.client.cosmetic.model.melon_mangler;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

@SuppressWarnings("FieldCanBeLocal")
@Environment(EnvType.CLIENT)
public class MelonManglerHatModel extends CosmeticModel {
    private final ModelPart hat;
    private final ModelPart midHat;
    private final ModelPart topHat;

    public MelonManglerHatModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        hat = new ModelPart(this);
        hat.setTextureOffset(0, 19).addCuboid(-10.0F, -9.0F, -10.0F, 20.0F, 3.0F, 20.0F, 0.0F, false);

        midHat = new ModelPart(this);
        midHat.setPivot(0.0F, -3.0F, 0.0F);
        hat.addChild(midHat);
        midHat.setTextureOffset(40, 0).addCuboid(-4.0F, -9.0F, -4.0F, 8.0F, 3.0F, 8.0F, 0.0F, false);

        topHat = new ModelPart(this);
        topHat.setPivot(0.0F, -3.0F, 0.0F);
        midHat.addChild(topHat);
        topHat.setTextureOffset(0, 0).addCuboid(-5.0F, -15.0F, -5.0F, 10.0F, 9.0F, 10.0F, 0.0F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.hat.copyPositionAndRotation(this.renderer.getModel().getHead());
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.hat);
    }
}