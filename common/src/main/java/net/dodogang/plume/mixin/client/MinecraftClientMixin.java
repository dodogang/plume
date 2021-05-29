package net.dodogang.plume.mixin.client;

import net.dodogang.plume.donor.client.cosmetic.gui.screen.ingame.CosmeticsScreen;
import net.dodogang.plume.client.PlumeKeyBindings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow public abstract void openScreen(@Nullable Screen screen);

    @Shadow @Nullable public ClientPlayerEntity player;

    @Inject(method = "tick", at = @At("RETURN"))
    private void endTick(CallbackInfo ci) {
        if (PlumeKeyBindings.OPEN_COSMETICS_MENU.isPressed() && this.player != null) {
            this.openScreen(new CosmeticsScreen(this.player));
        }
    }
}
