package net.dodogang.plume.ash.registry.forge;

import net.dodogang.plume.ash.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;

@ApiStatus.Internal
public final class FuelRegistryImpl {
    private static final Map<Item, Integer> ITEM_FUEL_TIMES = new HashMap<>();

    /**
     * Registers items as fuel with a burn time for furnace-like blocks.
     *
     * @param burnTime the item's burn time
     * @param items the items
     */
    public static void register(int burnTime, ItemConvertible... items) {
        for (ItemConvertible item : items) {
            ITEM_FUEL_TIMES.put(item.asItem(), burnTime);
        }
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
        return ForgeHooks.getBurnTime(new ItemStack(item));
    }

    public static void furnaceFuelBurnTimeHandler(FurnaceFuelBurnTimeEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (!itemStack.isEmpty() && event.getBurnTime() == 0) {
            event.setBurnTime(ITEM_FUEL_TIMES.getOrDefault(itemStack.getItem(), 0));
        }
    }
}
