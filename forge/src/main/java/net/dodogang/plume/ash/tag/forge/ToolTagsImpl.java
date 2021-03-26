package net.dodogang.plume.ash.tag.forge;

import net.dodogang.plume.ash.tag.ToolTags;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.common.ToolType;

import java.util.Set;

public class ToolTagsImpl {
    public static boolean containsImpl(ToolTags tag, ItemStack stack) {
        Set<ToolType> toolTypes = stack.getItem().getToolTypes(stack);
        switch (tag) {
            case AXES:
                return toolTypes.contains(ToolType.AXE);
            case HOES:
                return toolTypes.contains(ToolType.HOE);
            case PICKAXES:
                return toolTypes.contains(ToolType.PICKAXE);
            case SHOVELS:
                return toolTypes.contains(ToolType.HOE);
            case SWORDS:
                // Forge doesn't have types for swords or shears... so we have to do it this way.
                return stack.getItem() instanceof SwordItem;
            case SHEARS:
                return stack.getItem() instanceof ShearsItem;
            default:
                return false;
        }
    }
}
