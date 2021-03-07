package net.dodogang.plume.ash.registry.fabric;

import net.dodogang.plume.ash.registry.ItemGroupBuilder;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

public class ItemGroupBuilderImpl extends ItemGroupBuilder {
    public ItemGroupBuilderImpl(Identifier id) {
        super(id);
    }

    /**
     * Creates an ItemGroupBuilder.
     *
     * @param id The name of the ItemGroup and will be used in the translation key.
     * @return a ItemGroupBuilder
     */
    @ApiStatus.Internal
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
        return FabricItemGroupBuilder.create(id)
                .icon(iconSupplier)
                .appendItems(stacksToDisplay)
                .build();
    }
}
