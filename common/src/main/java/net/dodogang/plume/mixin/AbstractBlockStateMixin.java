package net.dodogang.plume.mixin;

import net.dodogang.plume.ash.tag.AshBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
    @Shadow public abstract boolean isIn(Tag<Block> tag);

    /**
     * Necessary for having modded bookshelves affect enchantment tables.
     *
     * This class' code is almost exactly copied from CorgiTaco's BYG mod:
     * https://github.com/CorgiTaco/BYG/blob/Fabric-1.17.X/src/main/java/corgiaoc/byg/mixin/common/block/MixinAbstractBlockStateBookshelf.java
     * which is licensed under LGPL v3. I have gotten explicit permission from
     * CorgiTaco to use this code in this mod.
     */
    @Inject(at = @At("HEAD"), method = "isOf", cancellable = true)
    private void isBookshelf(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (block.equals(Blocks.BOOKSHELF)) {
            if (AshBlockTags.BOOKSHELVES instanceof RequiredTagList.TagWrapper wrapper) {
                if (((RequiredTagListTagWrapperAccessor<?>) wrapper).getDelegate() != null) {
                    if (this.isIn(AshBlockTags.BOOKSHELVES)) cir.setReturnValue(true);
                }
            }
        }
    }
}
