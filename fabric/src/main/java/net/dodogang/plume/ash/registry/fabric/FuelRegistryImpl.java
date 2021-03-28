package net.dodogang.plume.ash.registry.fabric;

import net.dodogang.plume.ash.registry.FuelRegistry;
import net.minecraft.item.ItemConvertible;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class FuelRegistryImpl {
    /**
     * Registers an item as fuel with a burn time for furnace-like blocks.
     *
     * <p>Note: Undefined behavior if the item has been registered already by
     * vanilla or another mod. Expect this to not affect those items.
     *
     * @param item an item
     * @param burnTime the item's burn time
     */
    public static void register(ItemConvertible item, int burnTime) {
        net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.add(item, burnTime);
    }

    /**
     * Unregisters an item as fuel furnace-like blocks.
     *
     * <p>Note: This only unregisters it if it has been registered through
     * plume's {@link FuelRegistry}.
     *
     * @param item an item
     */
    public static void unregister(ItemConvertible item) {
        net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.remove(item);
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
