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
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class TabbedItemGroup extends ItemGroup {
    protected final Identifier id;
    private final Supplier<ItemStack> icon;
    private final Function<Identifier, List<ItemGroupTab>> tabsSupplier;

    private final List<ItemGroupTab> tabs = new ArrayList<>();
    private int selectedTabIndex = 0;
    private boolean initialized = false;

    public TabbedItemGroup(Identifier id, Function<Identifier, List<ItemGroupTab>> tabs, Supplier<ItemStack> icon) {
        super(TabbedItemGroup.getItemGroupIndex(id), id.getNamespace() + "." + id.getPath());

        this.id = id;
        this.tabsSupplier = tabs;
        this.icon = icon;
    }
    public TabbedItemGroup(String modId, Function<Identifier, List<ItemGroupTab>> tabs, Supplier<ItemStack> icon) {
        this(new Identifier(modId, "title"), tabs, icon);
    }

    private static int getItemGroupIndex(Identifier id) {
        ItemGroupBuilder.create(id).build();
        return ItemGroup.GROUPS.length - 1;
    }

    public static ItemGroupTab createTab(ItemConvertible item, String modId, String id) {
        return TabbedItemGroup.createTab(item, modId, id, TagRegistry.item(new Identifier(Plume.MOD_ID, "creative_tabs/" + modId + "/" + id)));
    }
    public static ItemGroupTab createTab(ItemConvertible item, String modId, String id, Tag<Item> tag) {
        return TabbedItemGroup.createTab(new ItemStack(item), modId, id, tag);
    }
    public static ItemGroupTab createTab(ItemStack stack, String modId, String id, Tag<Item> tag) {
        return new ItemGroupTab(stack, new Identifier(modId, id), tag);
    }

    @Override
    public void appendStacks(DefaultedList<ItemStack> stacks) {
        for (Item item : Registry.ITEM) {
            ItemGroupTab tab = this.getSelectedItemTab();
            if (tab.matches(item)) {
                this.appendStack(item, stacks);
            } else if (tab.getId().getPath().equals("all")) {
                for (ItemGroupTab i : tabs) {
                    if (i.matches(item) || item.getGroup() == this) {
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

    public void init() {
        tabs.add(createAllTab());
        tabs.addAll(tabsSupplier.apply(this.id));

        initialized = true;
    }

    @Environment(EnvType.CLIENT)
    public Identifier getIconBackgroundTexture() {
        return CreativeInventoryScreenAccessor.getTexture();
    }

    protected ItemGroupTab createAllTab() {
        return createTab(getIcon(), this.id.getNamespace(), "all", null);
    }

    public ItemGroupTab getSelectedItemTab() {
        return tabs.get(this.selectedTabIndex);
    }

    public List<ItemGroupTab> getTabs() {
        return this.tabs;
    }

    public int getSelectedTabIndex() {
        return this.selectedTabIndex;
    }

    public void setSelectedTabIndex(int selectedTabIndex) {
        this.selectedTabIndex = selectedTabIndex;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    @Override
    public Text getTranslationKey() {
        return this.selectedTabIndex != 0 ? new TranslatableText("itemGroup." + id.getNamespace(), this.getSelectedItemTab().getTranslationKey()) : super.getTranslationKey();
    }

    @Override
    public ItemStack createIcon() {
        return this.icon.get();
    }
}
