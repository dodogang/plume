package net.dodogang.plume.mixin.test.datagen;

import net.dodogang.plume.Plume;
import net.dodogang.plume.datagen.models.BlockStateTable;
import net.dodogang.plume.datagen.models.stategen.StateGen;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

import static net.dodogang.plume.datagen.models.modelgen.InheritingModelGen.*;
import static net.dodogang.plume.datagen.models.stategen.helper.SimpleBlocksStateHelper.*;

@Mixin(BlockStateTable.class)
public abstract class BlockStateTableMixin {
    @Shadow private static void register(Block block, Function<Block, StateGen> genFactory) {
        throw new AssertionError();
    }

    @Shadow
    private static String name(Block block, String nameFormat) {
        throw new AssertionError();
    }

    @Inject(method = "addAll", at = @At("HEAD"), remap = false)
    private static void addAll(CallbackInfo ci) {
        register(Plume.getTestBlock(),block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
    }
}
