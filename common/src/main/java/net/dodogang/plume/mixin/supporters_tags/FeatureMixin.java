package net.dodogang.plume.mixin.supporters_tags;

import net.dodogang.plume.tag.PlumeBlockTags;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.Feature;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Feature.class)
public class FeatureMixin {
    @Inject(method = "isSoil(Lnet/minecraft/block/Block;)Z", at = @At("RETURN"), cancellable = true)
    private static void isSoil(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (block.isIn(PlumeBlockTags.FEATURE_SUPPORTERS_SOIL)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isStone", at = @At("RETURN"), cancellable = true)
    private static void isStone(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (block.isIn(PlumeBlockTags.FEATURE_SUPPORTERS_STONE)) {
            cir.setReturnValue(true);
        }
    }
}
