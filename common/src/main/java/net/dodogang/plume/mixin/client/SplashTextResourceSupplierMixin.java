package net.dodogang.plume.mixin.client;

import net.dodogang.plume.client.registry.SplashTextRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Shadow @Final private List<String> splashTexts;

    @Inject(method = "apply", at = @At("TAIL"))
    private void appendSplashTexts(List<String> list, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        this.splashTexts.addAll(SplashTextRegistry.getSplashTexts());
    }
}
