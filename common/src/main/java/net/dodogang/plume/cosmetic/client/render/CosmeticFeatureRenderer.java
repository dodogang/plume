package net.dodogang.plume.cosmetic.client.render;

import net.dodogang.plume.cosmetic.Cosmetic;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.CosmeticsManager;
import net.dodogang.plume.util.PlayerUUID;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CosmeticFeatureRenderer<M extends CompositeEntityModel<PlayerEntity>> extends FeatureRenderer<PlayerEntity, PlayerEntityModel<PlayerEntity>> {
    protected final PlayerEntityRenderer renderer;
    protected final Cosmetic             cosmetic;
    protected final M                    model;
    protected final Identifier           texture;

    public CosmeticFeatureRenderer(PlayerEntityRenderer renderer, Cosmetic cosmetic, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>> ctx, M model, Identifier texture) {
        super(ctx);

        this.renderer = renderer;
        this.cosmetic = cosmetic;
        this.model    = model;
        this.texture  = texture;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertices, int light, PlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        CosmeticPlayerData cosmetics = CosmeticsManager.getLocalData(Util.parseStringUUID(entity.getUuid()));
        if (cosmetics != null && cosmetics.getCosmetics().containsValue(this.cosmetic)) {
            FeatureRenderer.render(this.getContextModel(), this.model, this.getTexture(entity), matrices, vertices, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch, 1.0f, 1.0f, 1.0f);
        }
    }

    public Cosmetic getCosmetic() {
        return this.cosmetic;
    }

    @Override
    protected Identifier getTexture(PlayerEntity entity) {
        return this.texture;
    }
}
