package net.dodogang.plume.ash.registry.forge;

import net.dodogang.plume.ash.registry.ItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.ApiStatus;

public class ItemGroupBuilderImpl extends ItemGroupBuilder {
    public ItemGroupBuilderImpl(Identifier id) {
        super(id);
    }

    @ApiStatus.Internal
    public static ItemGroupBuilder create(Identifier id) {
        return new ItemGroupBuilderImpl(id);
    }

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
