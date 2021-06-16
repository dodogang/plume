package net.dodogang.plume.datagen.models;

import net.dodogang.plume.datagen.models.modelgen.ModelGen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class ItemModelTable {
    private static BiConsumer<Item, ModelGen> consumer;

    public static void registerItemModels(BiConsumer<Item, ModelGen> c) {
        consumer = c;
        ItemModelTable.addAll();
    }

    /**
     * Mixin to add.
     */
    protected static void addAll() {}

    /*private static void registerWoodBlocks(WoodBlocks blocks) {
        register(blocks.PLANKS, item -> inherit(name(item, "block/%s")));
        register(blocks.SAPLING, item -> generated(name(item, "block/%s")));
        register(blocks.LOG, item -> inherit(name(item, "block/%s")));
        register(blocks.STRIPPED_LOG, item -> inherit(name(item, "block/%s")));
        register(blocks.WOOD, item -> inherit(name(item, "block/%s")));
        register(blocks.STRIPPED_WOOD, item -> inherit(name(item, "block/%s")));
        register(blocks.LEAVES, item -> inherit(name(item, "block/%s")));
        register(blocks.SLAB, item -> inherit(name(item, "block/%s")));
        register(blocks.STAIRS, item -> inherit(name(item, "block/%s")));
        register(blocks.FENCE, item -> fenceInventory(name(item, "block/%s_planks", "_fence")));
        register(blocks.DOOR, item -> generated(name(item, "item/%s")));
        register(blocks.TRAPDOOR, item -> inherit(name(item, "block/%s_bottom")));
        register(blocks.FENCE_GATE, item -> inherit(name(item, "block/%s")));
        register(blocks.PRESSURE_PLATE, item -> inherit(name(item, "block/%s")));
        register(blocks.BUTTON, item -> buttonInventory(name(item, "block/%s_planks", "_button")));
        register(blocks.SIGN_ITEM, item -> generated(name(item, "item/%s")));
        register(blocks.BOAT_ITEM, item -> generated(name(item, "item/%s")));
    }*/

    private static void register(ItemConvertible provider, Function<Item, ModelGen> genFactory) {
        Item item = provider.asItem();
        ModelGen gen = genFactory.apply(item);
        consumer.accept(item, gen);
    }

    private static String name(Item item, String nameFormat) {
        Identifier id = Registry.ITEM.getId(item);
        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, id.getPath()));
    }

    private static String name(Item item) {
        Identifier id = Registry.ITEM.getId(item);
        return id.toString();
    }

    private static String name(Item item, String nameFormat, String omitSuffix) {
        Identifier id = Registry.ITEM.getId(item);

        String path = id.getPath();
        if (path.endsWith(omitSuffix)) {
            path = path.substring(0, path.length() - omitSuffix.length());
        }

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, path));
    }

    private static String name(Item item, String nameFormat, String pattern, String reformat) {
        Identifier id = Registry.ITEM.getId(item);

        String path = id.getPath();
        path = path.replaceAll(pattern, reformat);

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, path));
    }

    private static <T> T using(String name, Function<String, T> func) {
        return func.apply(name);
    }

    private ItemModelTable() {
    }
}
