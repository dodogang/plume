package net.dodogang.plume;

import net.dodogang.plume.ash.registry.BatchedRegister;
import net.dodogang.plume.ash.registry.ItemGroupBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Plume {
    public static final String MOD_ID = "plume";

    public static final boolean runDevTests = true;

    public static void initialize() {
        if (runDevTests) {
            ItemGroup itemGroup = ItemGroupBuilder
                    .create(new Identifier(MOD_ID, "item_group"))
                    .icon(() -> new ItemStack(Blocks.DIRT))
                    .items(itemStacks -> itemStacks.add(new ItemStack(Blocks.DIRT)))
                    .build();

            BatchedRegister<Block> blockBatchedRegister = BatchedRegister.create(Registry.BLOCK_KEY, MOD_ID);
            BatchedRegister<Item> itemBatchedRegister = BatchedRegister.create(Registry.ITEM_KEY, MOD_ID);

            Block block = new Block(AbstractBlock.Settings.copy(Blocks.STONE));
            blockBatchedRegister.add("test_block", block);
            itemBatchedRegister.add("test_block", new BlockItem(block, new Item.Settings().group(itemGroup)));

            blockBatchedRegister.register();
            itemBatchedRegister.register();
        }
    }
}
