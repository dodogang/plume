package net.dodogang.plume.ash.registry.forge;

import net.dodogang.plume.ash.registry.ItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class ItemGroupBuilderImpl extends ItemGroupBuilder {
    public ItemGroupBuilderImpl(Identifier id) {
        super(id);
    }

    /**
     * Creates an ItemGroupBuilder.
     *
     * @param id The name of the ItemGroup and will be used in the translation key.
     * @return a ItemGroupBuilder
     */
    public static ItemGroupBuilder create(Identifier id) {
        return new ItemGroupBuilderImpl(id);
    }

    /**
     * Build the ItemGroup from the ItemGroupBuilder.
     *
     * @return the built ItemGroup
     */
    @Override
    public ItemGroup build() {
        return new ItemGroup(String.format("%s.%s", id.getNamespace(), id.getPath())) {
            @Override
            public ItemStack createIcon() {
                return iconSupplier.get();
            }

            @Override
            public void appendStacks(DefaultedList<ItemStack> items) {
                if (stacksToDisplay != null) {
                    stacksToDisplay.accept(items);
                    return;
                }
                super.appendStacks(items);
            }
        };
    }
}
