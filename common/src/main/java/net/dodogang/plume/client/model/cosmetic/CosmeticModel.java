package net.dodogang.plume.client.model.cosmetic;

import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.entity.player.PlayerEntity;

public abstract class CosmeticModel extends CompositeEntityModel<PlayerEntity> {
    protected final PlayerEntityRenderer renderer;

    public CosmeticModel(PlayerEntityRenderer renderer) {
        this.renderer = renderer;
    }
}
