package net.dodogang.plume.mixin.client;

import com.google.common.collect.ImmutableMap;
import net.dodogang.plume.client.registry.SplashTextRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.TitleScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Shadow @Nullable private String splashText;

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawCenteredString(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V", ordinal = 0), index = 5)
    private int modifySplashColor(int original) {
        ImmutableMap<String, Integer> map = SplashTextRegistry.getSplashTextColorMap();
        if (map.containsKey(this.splashText)) {
            return map.get(this.splashText);
        }

        return original;
    }
}
