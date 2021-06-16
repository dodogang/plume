package net.dodogang.plume.mixin.test.datagen;

import com.mojang.datafixers.util.Pair;
import net.dodogang.plume.datagen.loottables.PlumeLootTableDataProvider;
import net.dodogang.plume.test.datagen.PlumeBlockLootTableCreator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mixin(PlumeLootTableDataProvider.class)
public class PlumeLootTableDataProviderMixin {
    @Inject(method = "getLootTypeGenerators", at = @At("RETURN"), cancellable = true, remap = false)
    private static void addLootTypeGenerators(CallbackInfoReturnable<List<Pair<Supplier<Consumer<BiConsumer<Identifier, LootTable.Builder>>>, LootContextType>>> cir) {
        List<Pair<Supplier<Consumer<BiConsumer<Identifier, LootTable.Builder>>>, LootContextType>> generators = cir.getReturnValue();
        generators.add(Pair.of(PlumeBlockLootTableCreator::new, LootContextTypes.BLOCK));

        cir.setReturnValue(generators);
    }
}
