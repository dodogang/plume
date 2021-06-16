package net.dodogang.plume.mixin.test.datagen;

import net.dodogang.plume.Plume;
import net.dodogang.plume.datagen.models.ItemModelTable;
import net.dodogang.plume.datagen.models.modelgen.ModelGen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

import static net.dodogang.plume.datagen.models.modelgen.InheritingModelGen.*;

@Mixin(ItemModelTable.class)
public abstract class ItemModelTableMixin {
    @Shadow
    private static void register(ItemConvertible provider, Function<Item, ModelGen> genFactory) {
        throw new AssertionError();
    }

    @Shadow
    private static String name(Item item, String nameFormat) {
       throw new AssertionError();
    }

    @Inject(method = "addAll", at = @At("HEAD"), remap = false)
    private static void addAll(CallbackInfo ci) {
        register(Plume.getTestBlock(), item -> inherit(name(item, "block/%s")));
    }
}
