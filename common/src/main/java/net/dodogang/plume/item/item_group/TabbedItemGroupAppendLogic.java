package net.dodogang.plume.item.item_group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface TabbedItemGroupAppendLogic {
    void plume_appendStacksToTab(ItemGroup group, DefaultedList<ItemStack> stacks);
}
