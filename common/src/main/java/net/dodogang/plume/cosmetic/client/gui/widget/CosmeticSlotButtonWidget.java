package net.dodogang.plume.cosmetic.client.gui.widget;

import net.dodogang.plume.cosmetic.Cosmetic;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.CosmeticSlot;
import net.dodogang.plume.cosmetic.CosmeticsManager;
import net.dodogang.plume.cosmetic.client.gui.screen.ingame.CosmeticsScreen;
import net.dodogang.plume.util.PlayerUUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CosmeticSlotButtonWidget extends TexturedButtonWidget {
    private final CosmeticsScreen parent;
    private final CosmeticSlot slot;

    private final int u;
    private final int v;
    private final int hoveredVOffset;
    private final Identifier texture;

    public CosmeticSlotButtonWidget(CosmeticsScreen parent, CosmeticSlot slot, int x, int y, int u, int v, int hoveredVOffset, Identifier texture, PressAction onClick, TooltipSupplier tooltip) {
        super(x, y, 16, 16, u, v, hoveredVOffset, texture, 256, 256, onClick, tooltip, LiteralText.EMPTY);

        this.u = u;
        this.v = v;
        this.hoveredVOffset = hoveredVOffset;
        this.texture = texture;

        this.parent = parent;
        this.slot = slot;
    }

    public CosmeticSlot getSlot() {
        return this.slot;
    }

    @Override
    public void renderButton(MatrixStack matrices, int i, int j, float f) {
        boolean renderedCosmetic = false;
        CosmeticPlayerData data = CosmeticsManager.getLocalData(PlayerUUID.$CLIENT);
        if (data != null) {
            Cosmetic cosmetic = data.getCosmetics().get(this.slot);
            if (cosmetic != null) {
                MinecraftClient.getInstance().getTextureManager().bindTexture(cosmetic.texture);
                DrawableHelper.drawTexture(matrices, this.x, this.y, 0, 0, 16, 16, 16, 16);
                renderedCosmetic = true;
            }
        }

        int v = this.v;
        if (parent.getSelectedSlot() == this.slot) {
            v += this.hoveredVOffset * 2;
        } else if (this.isHovered()) {
            v += this.hoveredVOffset;
        }
        if (!renderedCosmetic) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(this.texture);
            drawTexture(matrices, this.x, this.y, this.u, v, this.width, this.height, 256, 256);
        }

        if (this.isHovered()) {
            this.renderToolTip(matrices, i, j);
        }
    }

    @Override
    public boolean isHovered() {
        CosmeticPlayerData data = CosmeticsManager.getLocalData(PlayerUUID.$CLIENT);
        if (data != null) {
            Cosmetic cosmetic = data.getCosmetics().get(this.slot);
            if (cosmetic != null) {
                return this.hovered;
            }
        }

        return super.isHovered() || parent.getSelectedSlot() == this.slot;
    }

    public boolean isJustHovered() {
        return this.hovered;
    }
}
