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

    @ApiStatus.Internal
    public static ItemGroupBuilder create(Identifier id) {
        return new ItemGroupBuilderImpl(id);
    }

    @Override
    public ItemGroup build() {
        return FabricItemGroupBuilder.create(id)
                .icon(iconSupplier)
                .appendItems(stacksToDisplay)
                .build();
    }
}
