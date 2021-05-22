package net.dodogang.plume.mixin.client;

import net.dodogang.plume.client.cosmetic.ClientCosmeticManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Environment(EnvType.CLIENT)
@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
    // there is only one armor feature renderer per-client, isn't multiplayer-friendly, this may as well be static TODO
    private UUID uuid = null;

    @SuppressWarnings("unused")
    private ArmorFeatureRendererMixin(FeatureRendererContext<T, M> ctx) {
        super(ctx);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(MatrixStack matrices, VertexConsumerProvider vertices, int i, T entity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        if (entity instanceof PlayerEntity) {
            this.uuid = entity.getUuid();
        }
    }

    // DOES NOT WORK ON FORGE!!! TODO
    @Inject(method = "renderArmorParts", at = @At("HEAD"), cancellable = true)
    private void cancelRender(MatrixStack matrices, VertexConsumerProvider vertices, int i, ArmorItem item, boolean dual, A model, boolean isOverlay, float f, float g, float h, String texture, CallbackInfo ci) {
        ClientCosmeticManager.checkToCancelArmorRender(this.uuid, item, ci);
    }
}
