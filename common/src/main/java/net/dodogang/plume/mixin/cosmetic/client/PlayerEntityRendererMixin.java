package net.dodogang.plume.mixin.cosmetic.client;

import net.dodogang.plume.cosmetic.client.CosmeticsManagerClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    @SuppressWarnings("unused")
    private PlayerEntityRendererMixin(EntityRenderDispatcher dispatcher, PlayerEntityModel<AbstractClientPlayerEntity> model, float slim) {
        super(dispatcher, model, slim);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRenderDispatcher;Z)V", at = @At("TAIL"))
    private void injectCosmetics(EntityRenderDispatcher dispatcher, boolean slim, CallbackInfo ci) {
        CosmeticsManagerClient.getRenderers().forEach((cosmetic, renderer) -> this.addFeature(renderer.apply(PlayerEntityRenderer.class.cast(this), (FeatureRendererContext) this)));
    }
}
