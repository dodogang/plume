package net.dodogang.plume.client.gui.item_group;

import net.dodogang.plume.Plume;
import net.dodogang.plume.item.item_group.TabbedItemGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record ItemGroupTab(@NotNull Identifier id, @NotNull ItemStack icon, @Nullable Tag<Item> tag) {
    private static final Identifier TAB_WIDGET_TEXTURE = new Identifier(Plume.MOD_ID, "textures/gui/creative_inventory/item_group/tab_widget.png");

    public ItemGroupTab(@NotNull Identifier id, @NotNull ItemStack icon, @Nullable Tag<Item> tag) {
        this.id = id;
        this.icon = icon;
        this.tag = tag;
    }

    public Identifier getWidgetBackgroundTexture() {
        return ItemGroupTab.TAB_WIDGET_TEXTURE;
    }

    public TranslatableText getTranslationKey() {
        return new TranslatableText("itemGroup.tab." + id);
    }

    public ItemGroupTabWidget createWidget(int x, int y, int index, TabbedItemGroup tab, CreativeInventoryScreen screen) {
        return new ItemGroupTabWidget(x, y, index, tab, screen, this.getWidgetBackgroundTexture());
    }

    public boolean contains(Item item) {
        return tag != null && tag.contains(item);
    }
}
