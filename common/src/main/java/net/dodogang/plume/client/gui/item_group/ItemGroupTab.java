package net.dodogang.plume.client.gui.item_group;

import net.dodogang.plume.Plume;
import net.dodogang.plume.item.item_group.TabbedItemGroup;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class ItemGroupTab {
    private final Tag<Item> tag;
    private final ItemStack icon;
    private final Identifier id;
    private Identifier widgetBackgroundTexture = new Identifier(Plume.MOD_ID, "textures/gui/creative_inventory/item_group/tab_widget.png");

    public ItemGroupTab(ItemStack icon, Identifier id, Tag<Item> tag) {
        this.tag = tag;
        this.icon = icon;
        this.id = id;
    }

    @SuppressWarnings("unused")
    public ItemGroupTab setWidgetBackgroundTexture(Identifier widgetBackgroundTexture) {
        this.widgetBackgroundTexture = widgetBackgroundTexture;
        return this;
    }

    public Identifier getId() {
        return this.id;
    }

    public TranslatableText getTranslationKey() {
        return new TranslatableText("itemGroup.tab." + id);
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public boolean matches(Item item) {
        return tag != null && tag.contains(item);
    }

    public ItemGroupTabWidget createWidget(int x, int y, int selectedTabIndex, TabbedItemGroup tab, CreativeInventoryScreen screen) {
        return new ItemGroupTabWidget(x, y, selectedTabIndex, tab, screen, this.widgetBackgroundTexture);
    }
}
