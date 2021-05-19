package net.dodogang.plume.mixin.supporters_tags;

import net.dodogang.plume.tag.PlumeBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.FungusBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FungusBlock.class)
public class FungusBlockMixin {
    @Inject(method = "canPlantOnTop", at = @At("RETURN"), cancellable = true)
    private void canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (floor.isIn(PlumeBlockTags.FUNGUS_SUPPORTERS)) {
            cir.setReturnValue(true);
        }
    }
}
