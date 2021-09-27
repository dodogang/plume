package net.dodogang.plume.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dodogang.plume.client.gui.item_group.ItemGroupTabWidget;
import net.dodogang.plume.item.item_group.TabbedItemGroup;
import net.dodogang.plume.mixin.ScreenAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler> {
    private final List<ItemGroupTabWidget> plume_tabWidgets = new ArrayList<>();

    private CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory inventory, Text text) {
        super(screenHandler, inventory, text);
    }

    @Inject(at = @At("HEAD"), method = "setSelectedTab(Lnet/minecraft/item/ItemGroup;)V")
    private void setSelectedTab(ItemGroup group, CallbackInfo ci) {
        ((ScreenAccessor) this).getDrawables().removeAll(this.plume_tabWidgets);
        this.plume_tabWidgets.clear();

        if (group instanceof TabbedItemGroup tabGroup) {
            tabGroup.refreshTabs();

            for (int i = 0; i < tabGroup.getTabs().size(); i++) {
                ItemGroupTabWidget widget = tabGroup.getTabs().get(i).createWidget(x, y, i, tabGroup, CreativeInventoryScreen.class.cast(this));

                if (i == tabGroup.getSelectedTabIndex()) {
                    widget.setSelected();
                }

                this.plume_tabWidgets.add(widget);
                this.addDrawableChild(widget);
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.plume_tabWidgets.forEach(
            w -> {
                if (w.isMouseOver(mouseX, mouseY)) {
                    this.renderTooltip(matrices, w.getMessage(), mouseX, mouseY);
                }
            }
        );
    }

    @Inject(method = "renderTabIcon", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/CreativeInventoryScreen;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", shift = At.Shift.BEFORE))
    protected void renderTabIcon(MatrixStack matrices, ItemGroup group, CallbackInfo ci) {
        if (this.client != null && group instanceof TabbedItemGroup) {
            RenderSystem.setShaderTexture(0, ((TabbedItemGroup) group).getIconBackgroundTexture());
        }
    }
}
