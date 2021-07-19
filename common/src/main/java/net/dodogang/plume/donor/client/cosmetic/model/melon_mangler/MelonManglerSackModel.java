package net.dodogang.plume.donor.client.cosmetic.model.melon_mangler;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class MelonManglerSackModel extends CosmeticModel {
    private final ModelPart sack;

    public MelonManglerSackModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        sack = new ModelPart(this);
        sack.setTextureOffset(88, 10).addCuboid(-5.0F, 0.3333F, 2.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
        sack.setTextureOffset(104, 28).addCuboid(-3.0F, -1.3333F, 4.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
        sack.setTextureOffset(88, 0).addCuboid(-4.0F, -3.3333F, 3.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.sack.copyPositionAndRotation(this.renderer.getModel().torso);
        setRotationAngle(this.sack, this.sack.pitch + -0.1309F, this.sack.yaw, this.sack.roll);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.sack);
    }
}
