package net.dodogang.plume;

import net.dodogang.ash.registry.ItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class Plume {
    public static final String MOD_ID = "plume";

    public static final boolean runDevTests = false;

    public static void init() {
        initTest();
    }

    private static void initTest() {
        if (!runDevTests) return;

        ItemGroupBuilder.create(new Identifier(MOD_ID, "item_group"))
                .icon(() -> new ItemStack(Items.ANDESITE))
                .appendItems(itemStacks -> {
                    itemStacks.add(new ItemStack(Items.ANDESITE_STAIRS));
                }).build();
    }
}
