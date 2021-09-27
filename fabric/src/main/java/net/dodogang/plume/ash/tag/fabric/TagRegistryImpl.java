package net.dodogang.plume.ash.tag.fabric;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
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
    public static Tag.Identified<Block> block(Identifier id) {
        return net.fabricmc.fabric.api.tag.TagFactory.BLOCK.create(id);
    }

    /**
     * Registers a entity type tag with the given identifier.
     *
     * @param id the identifier
     * @return the created entity type tag
     */
    public static Tag.Identified<EntityType<?>> entityType(Identifier id) {
        return net.fabricmc.fabric.api.tag.TagFactory.ENTITY_TYPE.create(id);
    }

    /**
     * Registers a fluid tag with the given identifier.
     *
     * @param id the identifier
     * @return the created fluid tag
     */
    public static Tag.Identified<Fluid> fluid(Identifier id) {
        return net.fabricmc.fabric.api.tag.TagFactory.FLUID.create(id);
    }

    /**
     * Registers a item tag with the given identifier.
     *
     * @param id the identifier
     * @return the created item tag
     */
    public static Tag.Identified<Item> item(Identifier id) {
        return net.fabricmc.fabric.api.tag.TagFactory.ITEM.create(id);
    }
}
