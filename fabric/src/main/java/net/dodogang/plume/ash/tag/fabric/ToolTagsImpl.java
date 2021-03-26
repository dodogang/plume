package net.dodogang.plume.ash.tag.fabric;

import net.dodogang.plume.ash.tag.ToolTags;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.item.ItemStack;

public class ToolTagsImpl {
    public static boolean containsImpl(ToolTags tag, ItemStack stack) {
        switch (tag) {
            case AXES:
                return FabricToolTags.AXES.contains(stack.getItem());
            case HOES:
                return FabricToolTags.HOES.contains(stack.getItem());
            case PICKAXES:
                return FabricToolTags.PICKAXES.contains(stack.getItem());
            case SHOVELS:
                return FabricToolTags.SHOVELS.contains(stack.getItem());
            case SWORDS:
                return FabricToolTags.SWORDS.contains(stack.getItem());
            case SHEARS:
                return FabricToolTags.SHEARS.contains(stack.getItem());
            default:
                return false;
        }
    }
}
