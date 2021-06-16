package net.dodogang.plume.datagen.tags;

import net.dodogang.plume.Plume;
import net.dodogang.plume.datagen.tags.factory.TagFactory;
import net.dodogang.plume.datagen.tags.factory.TagStore;
import net.minecraft.block.Block;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.Registry;

import java.io.IOException;

@SuppressWarnings("unused")
public class PlumeBlockItemTagProvider implements DataProvider {
    protected final TagStore<Block> blocks;
    protected final TagStore<Item> items;

    public PlumeBlockItemTagProvider(DataGenerator generator) {
        blocks = new TagStore<>(Registry.BLOCK, generator.getOutput(), "blocks");
        items = new TagStore<>(Registry.ITEM, generator.getOutput(), "items");
    }

    @Override
    public void run(DataCache cache) throws IOException {
        this.addAll(cache);

        blocks.write(cache);
        items.write(cache);
    }

    /**
     * Mixin to add.
     */
    protected void addAll(DataCache cache) {}

    /*private void addWoodSet(WoodBlocks set, Tag.Identified<Block> logsBlockTag, Tag.Identified<Item> logsItemTag) {
        add(BlockTags.PLANKS, ItemTags.PLANKS, set.PLANKS);
        add(BlockTags.SAPLINGS, ItemTags.SAPLINGS, set.SAPLING);
        add(BlockTags.FLOWER_POTS, set.POTTED_SAPLING);
        add(logsBlockTag, logsItemTag, set.LOG, set.STRIPPED_LOG, set.WOOD, set.STRIPPED_WOOD);
        if (set.isFlammable()) {
            blocks.factory(BlockTags.LOGS_THAT_BURN).add(logsBlockTag);
            items.factory(ItemTags.LOGS_THAT_BURN).add(logsItemTag);
        } else {
            blocks.factory(BlockTags.LOGS).add(logsBlockTag);
            items.factory(ItemTags.LOGS).add(logsItemTag);
            add(
                BlockTags.NON_FLAMMABLE_WOOD, ItemTags.NON_FLAMMABLE_WOOD,
                set.LOG, set.STRIPPED_LOG, set.WOOD, set.STRIPPED_WOOD
            );
        }
        add(BlockTags.LEAVES, ItemTags.LEAVES, set.LEAVES);
        add(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS, set.SLAB);
        add(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES, set.PRESSURE_PLATE);
        add(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES, set.FENCE);
        add(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS, set.TRAPDOOR);
        add(BlockTags.FENCE_GATES, set.FENCE_GATE);
        add(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS, set.STAIRS);
        add(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS, set.BUTTON);
        add(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS, set.DOOR);
        add(BlockTags.STANDING_SIGNS, set.SIGN);
        add(BlockTags.WALL_SIGNS, set.WALL_SIGN);
        add(ItemTags.SIGNS, set.SIGN_ITEM);
        add(ItemTags.BOATS, set.BOAT_ITEM);
    }*/

    @SafeVarargs
    private final void add(Block block, Tag<Block>... tags) {
        for (Tag<Block> tag : tags) {
            add(tag, block);
        }
    }

    @SafeVarargs
    private final void add(ItemConvertible item, Tag<Item>... tags) {
        for (Tag<Item> tag : tags) {
            add(tag, item);
        }
    }

    private void add(Tag<Block> blockTag, Block... vals) {
        TagFactory<Block> factory = blocks.factory(blockTag);
        for (Block val : vals) {
            factory.add(val);
        }
    }

    private void add(Tag<Item> itemTag, ItemConvertible... vals) {
        TagFactory<Item> factory = items.factory(itemTag);
        for (ItemConvertible val : vals) {
            factory.add(val.asItem());
        }
    }

    private void add(Tag<Block> blockTag, Tag<Item> itemTag, Block... vals) {
        TagFactory<Block> blockFac = blocks.factory(blockTag);
        TagFactory<Item> itemFac = items.factory(itemTag);
        for (Block val : vals) {
            blockFac.add(val);
            itemFac.add(val.asItem());
        }
    }

    @Override
    public String getName() {
        return Plume.MOD_NAME + "BlockItemTags";
    }
}
