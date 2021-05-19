package net.dodogang.plume.ash.tag.forge;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.*;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class TagRegistryImpl {
    /**
     * Registers a block tag with the given identifier.
     *
     * @param id the identifier
     * @return the created block tag
     */
    public static Tag<Block> block(Identifier id) {
        return BlockTags.register(id.toString());
    }

    /**
     * Registers a entity type tag with the given identifier.
     *
     * @param id the identifier
     * @return the created entity type tag
     */
    public static Tag<EntityType<?>> entityType(Identifier id) {
        return EntityTypeTags.register(id.toString());
    }

    /**
     * Registers a fluid tag with the given identifier.
     *
     * @param id the identifier
     * @return the created fluid tag
     */
    public static Tag<Fluid> fluid(Identifier id) {
        return FluidTags.register(id.toString());
    }

    /**
     * Registers a item tag with the given identifier.
     *
     * @param id the identifier
     * @return the created item tag
     */
    public static Tag<Item> item(Identifier id) {
        return ItemTags.register(id.toString());
    }
}
