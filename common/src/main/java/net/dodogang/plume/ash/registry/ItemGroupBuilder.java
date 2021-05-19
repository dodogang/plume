package net.dodogang.plume.ash.registry;

import me.shedaniel.architectury.annotations.ExpectPlatform;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@ApiStatus.NonExtendable
public abstract class ItemGroupBuilder {
    protected final Identifier id;
    protected Supplier<ItemStack> iconSupplier;
    protected Consumer<List<ItemStack>> stacksToDisplay;

    protected ItemGroupBuilder(Identifier id) {
        this.id      = id;
        iconSupplier = () -> ItemStack.EMPTY;
    }

    /**
     * Creates an ItemGroupBuilder.
     *
     * @param id The name of the ItemGroup and will be used in the translation key.
     * @return a ItemGroupBuilder
     */
    @ExpectPlatform
    public static ItemGroupBuilder create(Identifier id) {
        throw new AssertionError();
    }

    /**
     * Used to add an icon to the item group.
     *
     * @param iconSupplier A supplier that returns the item stack that will be shown on the tab.
     * @return the ItemGroupBuilder
     */
    public ItemGroupBuilder icon(Supplier<ItemStack> iconSupplier) {
        this.iconSupplier = iconSupplier;
        return this;
    }

    /**
     * Sets a custom list of items to be displayed in the item group.
     *
     * @param stacksToDisplay Add ItemStack's to this list to show in the ItemGroup.
     * @return the ItemGroupBuilder
     */
    public ItemGroupBuilder items(Consumer<List<ItemStack>> stacksToDisplay) {
        this.stacksToDisplay = stacksToDisplay;
        return this;
    }

    /**
     * Creates an ItemGroup in one method call with an icon.
     *
     * @param id The name of the ItemGroup and will be used in the translation key.
     * @param iconSupplier A supplier that returns the item stack that will be shown on the tab.
     * @return the built ItemGroup
     */
    public static ItemGroup build(Identifier id, Supplier<ItemStack> iconSupplier) {
        return create(id).icon(iconSupplier).build();
    }

    /**
     * Build the ItemGroup from the ItemGroupBuilder.
     *
     * @return the built ItemGroup
     */
    public abstract ItemGroup build();
}
