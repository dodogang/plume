package net.dodogang.plume.ash.registry.fabric;

import net.dodogang.plume.ash.registry.FuelRegistry;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Internal
public final class FuelRegistryImpl {
    /**
     * Registers items as fuel with a burn time for furnace-like blocks.
     *
     * @param burnTime the item's burn time
     * @param items items
     */
    public static void register(int burnTime, ItemConvertible... items) {
        for (ItemConvertible item : items) {
            net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.add(item, burnTime);
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
    public static int getBurnTime(ItemConvertible item, @Nullable RecipeType<?> recipeType) {
        return net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.get(item);
    }
}
