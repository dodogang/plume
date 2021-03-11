package net.dodogang.plume;

import net.dodogang.plume.ash.registry.ItemGroupBuilder;
import net.dodogang.plume.registry.BlockBatchedRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Plume {
    public static final String MOD_ID = "plume";

    public static final boolean runDevTests = false;

    public static void initialize() {
        if (runDevTests) {
            ItemGroup itemGroup = ItemGroupBuilder
                    .create(new Identifier(MOD_ID, "item_group"))
                    .icon(() -> new ItemStack(Blocks.DIRT))
                    .items(itemStacks -> itemStacks.add(new ItemStack(Blocks.DIRT)))
                    .build();

            BlockBatchedRegistry registry = new BlockBatchedRegistry(MOD_ID);
            registry.setDefaultItemSettings(new Item.Settings().group(itemGroup));

            registry.add("test_block", new Block(AbstractBlock.Settings.copy(Blocks.STONE)));

            registry.register();
        }
    }
}
