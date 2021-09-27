package net.dodogang.plume.item.item_group;

import net.dodogang.plume.Plume;
import net.dodogang.plume.ash.registry.ItemGroupBuilder;
import net.dodogang.plume.ash.tag.TagRegistry;
import net.dodogang.plume.client.gui.item_group.ItemGroupTab;
import net.dodogang.plume.mixin.client.CreativeInventoryScreenAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TabbedItemGroup extends ItemGroup {
    protected final Identifier id;
    protected final Function<TabbedItemGroup, ItemStack> icon;
    protected final Function<TabbedItemGroup, List<ItemGroupTab>> tabs;

    private final List<ItemGroupTab> cachedTabs = new ArrayList<>();
    private int selectedTabIndex = 0;

    public TabbedItemGroup(Identifier id, Function<TabbedItemGroup, List<ItemGroupTab>> tabs, Function<TabbedItemGroup, ItemStack> icon) {
        super(TabbedItemGroup.getItemGroupIndex(id), id.getNamespace() + "." + id.getPath());

        this.id = id;
        this.tabs = tabs;
        this.icon = icon;
    }
    public TabbedItemGroup(String modId, Function<TabbedItemGroup, List<ItemGroupTab>> tabs, Function<TabbedItemGroup, ItemStack> icon) {
        this(new Identifier(modId, "title"), tabs, icon);
    }

    private static int getItemGroupIndex(Identifier id) {
        ItemGroupBuilder.create(id).build();
        return ItemGroup.GROUPS.length - 1;
    }

    public ItemGroupTab createTab(String id, ItemConvertible icon) {
        return this.createTab(id, icon, TagRegistry.item(new Identifier(Plume.MOD_ID, "creative_tabs/" + this.id.getNamespace() + "/" + id)));
    }
    public ItemGroupTab createTab(String id, ItemConvertible icon, Tag<Item> tag) {
        return this.createTab(id, new ItemStack(icon), tag);
    }
    public ItemGroupTab createTab(String id, ItemStack icon, Tag<Item> tag) {
        return new ItemGroupTab(new Identifier(this.id.getNamespace(), id), icon, tag);
    }

    @Override
    public void appendStacks(DefaultedList<ItemStack> stacks) {
        for (Item item : Registry.ITEM) {
            ItemGroupTab tab = this.getSelectedItemTab();
            if (tab.contains(item)) {
                this.appendStack(item, stacks);
            } else if (tab.tag() == null) {
                for (ItemGroupTab i : this.getTabs()) {
                    if (i.contains(item) || item.getGroup() == this) {
                        this.appendStack(item, stacks);
                        break;
                    }
                }
            }
        }
    }
    protected void appendStack(Item item, DefaultedList<ItemStack> stacks) {
        if (item instanceof TabbedItemGroupAppendLogic) {
            ((TabbedItemGroupAppendLogic)item).plume_appendStacksToTab(this, stacks);
        } else {
            stacks.add(new ItemStack(item));
        }
    }

    public List<ItemGroupTab> getTabs() {
        return this.cachedTabs;
    }

    public void refreshTabs() {
        this.cachedTabs.clear();

        this.cachedTabs.add(this.createTab("all", this.getIcon(), null));
        this.cachedTabs.addAll(this.tabs.apply(this));
    }

    public int getSelectedTabIndex() {
        return this.selectedTabIndex;
    }
    public void setSelectedTabIndex(int selectedTabIndex) {
        this.selectedTabIndex = selectedTabIndex;
    }

    public ItemGroupTab getSelectedItemTab() {
        return this.getTabs().get(this.getSelectedTabIndex());
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Text getTranslationKey() {
        return this.selectedTabIndex != 0
            ? new TranslatableText("itemGroup." + id.getNamespace(), this.getSelectedItemTab().getTranslationKey())
            : super.getTranslationKey();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public ItemStack createIcon() {
        return this.icon.apply(this);
    }

    @Environment(EnvType.CLIENT)
    public Identifier getIconBackgroundTexture() {
        return CreativeInventoryScreenAccessor.getTexture();
    }
}
