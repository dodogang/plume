package net.dodogang.plume.ash.tag;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagRegistry {
    private TagRegistry() {}

    /**
     * Registers a block tag with the given identifier.
     *
     * @param id the identifier
     * @return the created block tag
     */
    @ExpectPlatform
    public static Tag<Block> block(Identifier id) {
        throw new AssertionError();
    }

    /**
     * Registers a entity type tag with the given identifier.
     *
     * @param id the identifier
     * @return the created entity type tag
     */
    @ExpectPlatform
    public static Tag<EntityType<?>> entityType(Identifier id) {
        throw new AssertionError();
    }

    /**
     * Registers a fluid tag with the given identifier.
     *
     * @param id the identifier
     * @return the created fluid tag
     */
    @ExpectPlatform
    public static Tag<Fluid> fluid(Identifier id) {
        throw new AssertionError();
    }

    /**
     * Registers a item tag with the given identifier.
     *
     * @param id the identifier
     * @return the created item tag
     */
    @ExpectPlatform
    public static Tag<Item> items(Identifier id) {
        throw new AssertionError();
    }
}
