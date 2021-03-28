package net.dodogang.plume.ash.registry;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.item.ItemConvertible;

public final class FuelRegistry {
    private FuelRegistry() {}

    /**
     * Registers an item as fuel with a burn time for furnace-like blocks.
     *
     * <p>Note: Undefined behavior if the item has been registered already by
     * vanilla or another mod. Expect this to not affect those items.
     *
     * @param item an item
     * @param burnTime the item's burn time
     */
    @ExpectPlatform
    public static void register(ItemConvertible item, int burnTime) {
        throw new AssertionError();
    }

    /**
     * Unregisters an item as fuel furnace-like blocks.
     *
     * <p>Note: This only unregisters it if it has been registered through
     * plume's {@link FuelRegistry}.
     *
     * @param item an item
     */
    @ExpectPlatform
    public static void unregister(ItemConvertible item) {
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
