package net.dodogang.plume.ash.registry.fabric;

import net.dodogang.plume.ash.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tag.Tag;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class FuelRegistryImpl {
    /**
     * Registers items as fuel with a burn time for furnace-like blocks.
     *
     * <p>If an item has already been registered a burn time by vanilla or
     * another mod, this doesn't overwrite it.
     *
     * @param burnTime the item's burn time
     * @param items items
     */
    public static void register(int burnTime, ItemConvertible... items) {
        for (ItemConvertible item : items) {
            if (net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.get(item) < 0) {
                net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.add(item, burnTime);
            }
        }
    }

    /**
     * Registers items from a tag as fuel with a burn time for furnace-like
     * blocks.
     *
     * <p>If an item has already been registered a burn time by vanilla or
     * another mod, this doesn't overwrite it.
     *
     * @param burnTime the item's burn time
     * @param itemTag an item tag
     */
    public static void registerTag(int burnTime, Tag<Item> itemTag) {
        net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.add(itemTag, burnTime);
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
    public static int getBurnTime(ItemConvertible item) {
        return net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.get(item);
    }
}
