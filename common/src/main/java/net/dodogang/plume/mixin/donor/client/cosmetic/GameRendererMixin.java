package net.dodogang.plume.mixin.donor.client.cosmetic;

import net.dodogang.plume.donor.client.cosmetic.gui.screen.ingame.CosmeticsScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "renderHand", at = @At("HEAD"), cancellable = true)
    private void cancelRenderHand(MatrixStack matrixStack, Camera camera, float f, CallbackInfo ci) {
        CosmeticsScreen.cancelOtherRenders(this.client, ci);
    }
}
