package net.dodogang.plume.mixin.donor.client.cosmetic;

import net.dodogang.plume.donor.client.cosmetic.gui.screen.ingame.CosmeticsScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void cancelRenderHotbar(float tickDelta, MatrixStack matrices, CallbackInfo ci) {
        CosmeticsScreen.cancelOtherRenders(this.client, ci);
    }

    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"), cancellable = true)
    private void cancelRenderHeldItemTooltip(MatrixStack matrices, CallbackInfo ci) {
        CosmeticsScreen.cancelOtherRenders(this.client, ci);
    }

    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    private void cancelRenderStatusBars(MatrixStack matrices, CallbackInfo ci) {
        CosmeticsScreen.cancelOtherRenders(this.client, ci);
    }
    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void cancelRenderExperienceBar(MatrixStack matrices, int x, CallbackInfo ci) {
        CosmeticsScreen.cancelOtherRenders(this.client, ci);
    }
}
