package net.dodogang.plume.mixin.cosmetic.client;

import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(CapeFeatureRenderer.class)
public class CapeFeatureRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void cancelRender(MatrixStack matrices, VertexConsumerProvider vertices, int i, AbstractClientPlayerEntity entity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        CosmeticsClient.cancelCapeRender(entity, ci);
    }
}
