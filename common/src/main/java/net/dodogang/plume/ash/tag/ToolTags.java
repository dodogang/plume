package net.dodogang.plume.ash.tag;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.item.ItemStack;

/**
 * If you want to add your own tool tag, just use the vanilla tag system. This
 * only exists because forge doesn't use tags... I plan on making a forge RFC
 * to get that changed for 1.17.
 *
 * TODO: Adding mod items to forge tool types. Fabric ones can be done through
 * their tags.
 */
public enum ToolTags {
    AXES,
    HOES,
    PICKAXES,
    SHOVELS,
    SWORDS,
    SHEARS;

    /**
     * Determines if an item stack contains a specific tool tag.
     *
     * @return true if it is in the tool tag.
     */
    public boolean contains(ItemStack stack) {
        return containsImpl(this, stack);
    }

    @ExpectPlatform
    private static boolean containsImpl(ToolTags tag, ItemStack stack) {
        throw new AssertionError();
    }
}
