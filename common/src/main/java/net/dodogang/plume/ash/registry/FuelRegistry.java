package net.dodogang.plume.ash.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeType;
import org.jetbrains.annotations.Nullable;

public final class FuelRegistry {
    private FuelRegistry() {}

    /**
     * Registers items as fuel with a burn time for furnace-like blocks.
     *
     * @param burnTime the item's burn time
     * @param items the items
     */
    @ExpectPlatform
    public static void register(int burnTime, ItemConvertible... items) {
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
    public static int getBurnTime(ItemConvertible item, @Nullable RecipeType<?> recipeType) {
        throw new AssertionError();
    }
    public static int getBurnTime(ItemConvertible item) {
        return getBurnTime(item, null);
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
