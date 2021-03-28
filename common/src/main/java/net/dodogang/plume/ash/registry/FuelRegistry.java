package net.dodogang.plume.ash.registry;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tag.Tag;

public final class FuelRegistry {
    private FuelRegistry() {}

    /**
     * Registers items as fuel with a burn time for furnace-like blocks.
     *
     * <p>If an item has already been registered a burn time by vanilla or
     * another mod, this doesn't overwrite it.
     *
     * @param burnTime the item's burn time
     * @param items the items
     */
    @ExpectPlatform
    public static void register(int burnTime, ItemConvertible... items) {
        throw new AssertionError();
    }

    /**
     * Registers items from a tag as fuel with a burn time for furnace-like
     * blocks.
     *
     * <p>If the item has already been registered a burn time by vanilla or
     * another mod, this doesn't overwrite it.
     *
     * @param burnTime the item's burn time
     * @param itemTag an item tag
     */
    @ExpectPlatform
    public static void registerTag(int burnTime, Tag<Item> itemTag) {
        throw new AssertionError();
    }

    /**
     * Returns the burn time of an item.
     *
     * <p>Note: This returns the burn time of any item, even if it was not
     * registered with plume's {@link FuelRegistry}.
     *
     * @param item an item
     * @return the burn time of the item. 0 if the item isn't fuel.
     */
    @ExpectPlatform
    public static int getBurnTime(ItemConvertible item) {
        throw new AssertionError();
    }

    /**
     * Returns whether the given item has a burn time of greater than zero.
     *
     * @param item an item
     * @return true if the item is fuel
     */
    public static boolean isFuel(ItemConvertible item) {
        return getBurnTime(item) > 0;
    }
}
