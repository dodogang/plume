package net.dodogang.plume.mixin.client;

import com.google.common.collect.Lists;
import net.dodogang.plume.client.gui.item_group.ItemGroupTabWidget;
import net.dodogang.plume.item.item_group.TabbedItemGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler> {
    private final List<ItemGroupTabWidget> plume_tabButtons = Lists.newArrayList();

    @SuppressWarnings("unused")
    public CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory inventory, Text text) {
        super(screenHandler, inventory, text);
    }

    @Inject(at = @At("HEAD"), method = "setSelectedTab(Lnet/minecraft/item/ItemGroup;)V")
    private void setSelectedTab(ItemGroup group, CallbackInfo ci) {
        buttons.removeAll(plume_tabButtons);
        plume_tabButtons.clear();

        if (group instanceof TabbedItemGroup) {
            TabbedItemGroup tab = (TabbedItemGroup) group;
            if (!tab.isInitialized()) {
                tab.init();
            }

            for (int i = 0; i < tab.getTabs().size(); i++) {
                ItemGroupTabWidget tabWidget = tab.getTabs().get(i).createWidget(x, y, i, tab, CreativeInventoryScreen.class.cast(this));

                if (i == tab.getSelectedTabIndex()) {
                    tabWidget.isSelected = true;
                }

                plume_tabButtons.add(tabWidget);
                addButton(tabWidget);
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta, CallbackInfo cbi) {
        plume_tabButtons.forEach(b -> {
            if (b.isHovered()) {
                renderTooltip(matrixStack, b.getMessage(), mouseX, mouseY);
            }
        });
    }

    @Inject(method = "renderTabIcon", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/CreativeInventoryScreen;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", shift = At.Shift.BEFORE))
    protected void renderTabIcon(MatrixStack matrixStack, ItemGroup itemGroup, CallbackInfo ci) {
        if (itemGroup instanceof TabbedItemGroup) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(((TabbedItemGroup) itemGroup).getIconBackgroundTexture());
        }
    }
}
