package net.dodogang.plume.donor.client.cosmetic.model.nautilus;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;

public class NautilusBackpackModel extends CosmeticModel {
    private final ModelPart backpack;

    public NautilusBackpackModel(PlayerEntityRenderer renderer) {
        super(renderer);

        this.textureWidth = 128;
        this.textureHeight = 128;

        backpack = new ModelPart(this);
        backpack.setTextureOffset(48, 9).addCuboid(-4.0F, 0.0F, 2.0F, 8.0F, 11.0F, 11.0F, 0.0F, false);
        backpack.setTextureOffset(48, 0).addCuboid(-4.0F, -1.0F, 5.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        this.backpack.copyPositionAndRotation(this.renderer.getModel().torso);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.backpack);
    }
}
