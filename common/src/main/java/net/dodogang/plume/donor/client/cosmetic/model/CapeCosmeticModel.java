package net.dodogang.plume.donor.client.cosmetic.model;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.mixin.donor.client.cosmetic.PlayerEntityModelAccessor;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public abstract class CapeCosmeticModel extends CosmeticModel {
    private final CapeFeatureRenderer renderer;

    public CapeCosmeticModel(CapeFeatureRenderer renderer) {
        super(null);
        this.renderer = renderer;
    }

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        this.getCape().copyPositionAndRotation(((PlayerEntityModelAccessor) this.renderer.getContextModel()).getCape());
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.getCape());
    }

    public abstract ModelPart getCape();

    public abstract Identifier getTexture();
}
