package net.dodogang.plume.donor.client.cosmetic.model;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.mixin.donor.client.cosmetic.PlayerEntityModelAccessor;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public abstract class CloakCosmeticModel extends CosmeticModel {
    private final CapeFeatureRenderer renderer;

    public CloakCosmeticModel(CapeFeatureRenderer renderer) {
        super(null);
        this.renderer = renderer;
    }

    @Override
    public void setAngles(PlayerEntity entity, float f, float g, float h, float i, float j) {
        this.getCloak().copyPositionAndRotation(((PlayerEntityModelAccessor) this.renderer.getContextModel()).getCape());
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.getCloak());
    }

    public abstract ModelPart getCloak();

    public abstract Identifier getTexture();
}
