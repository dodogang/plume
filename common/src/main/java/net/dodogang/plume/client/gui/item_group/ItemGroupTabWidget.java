package net.dodogang.plume.client.gui.item_group;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dodogang.plume.item.item_group.TabbedItemGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ItemGroupTabWidget extends ButtonWidget {
    protected final ItemGroupTab parent;
    protected final Identifier texture;
    protected boolean isSelected = false;

    public ItemGroupTabWidget(int x, int y, ItemGroupTab parent, PressAction onPress, Identifier texture) {
        super(x, y, 22, 22, parent.getTranslationKey(), onPress);
        this.parent = parent;
        this.texture = texture;
    }
    public ItemGroupTabWidget(int x, int y, int index, TabbedItemGroup parent, CreativeInventoryScreen screen, Identifier texture) {
        this(x - 24, (y + 12) + (index * 24), parent.getTabs().get(index), btn -> ItemGroupTabWidget.setSelected(btn, parent, index, screen), texture);
    }

    public static void setSelected(ButtonWidget widget, TabbedItemGroup tabGroup, int index, CreativeInventoryScreen screen) {
        ((ItemGroupTabWidget) widget).setSelected();
        tabGroup.setSelectedTabIndex(index);

        if (screen != null) {
            MinecraftClient.getInstance().openScreen(screen);
        }
    }
    public void setSelected() {
        this.isSelected = true;
    }

    @Override
    protected int getYImage(boolean isHovered) {
        return isHovered || this.isSelected ? 1 : 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getTextureManager().bindTexture(texture);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);

        this.drawTexture(matrices, this.x, this.y, 0, this.getYImage(this.isHovered()) * height, this.width, this.height);
        this.renderBg(matrices, client, mouseX, mouseY);

        client.getItemRenderer().renderInGui(parent.getIcon(), this.x + 3, this.y + 3);
    }
}
