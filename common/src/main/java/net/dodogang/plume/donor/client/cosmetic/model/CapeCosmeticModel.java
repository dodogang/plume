package net.dodogang.plume.donor.client.cosmetic.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public abstract class CapeCosmeticModel extends CosmeticModel {
    public CapeCosmeticModel() {
        super(null);
    }

    @Override
    public abstract Iterable<ModelPart> getParts();

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {}

    public abstract Identifier getTexture();
}
