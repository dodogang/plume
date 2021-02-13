package net.dodogang.plume;

import net.dodogang.ash.registry.BatchRegister;
import net.dodogang.ash.registry.ItemGroupBuilder;
import net.dodogang.ash.registry.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Plume {
    public static final String MOD_ID = "plume";

    public static final boolean runDevTests = false;

    public static void init() {
        initTest();
    }

    private static void initTest() {
        if (!runDevTests) return;

        ItemGroup itemGroup = ItemGroupBuilder.create(new Identifier(MOD_ID, "item_group"))
                .icon(() -> new ItemStack(Items.ANDESITE))
                .build();

        BatchRegister<Block> blockBatchRegister = BatchRegister.create(Registry.BLOCK_KEY, MOD_ID);
        BatchRegister<Item> itemBatchRegister = BatchRegister.create(Registry.ITEM_KEY, MOD_ID);

        final TestBlock block = new TestBlock(AbstractBlock.Settings.copy(Blocks.DIRT));
        RegistrySupplier<TestBlock> testBlock = blockBatchRegister.add("test", () -> block);
        RegistrySupplier<BlockItem> testBlockItem = itemBatchRegister.add(
                "test", () -> new BlockItem(block, new Item.Settings().group(itemGroup))
        );

        blockBatchRegister.register();
        itemBatchRegister.register();
    }

    private static class TestBlock extends Block {
        public TestBlock(Settings settings) {
            super(settings);
        }
    }
}
