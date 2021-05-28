package net.dodogang.plume.cosmetic.client.gui.widget;

import net.dodogang.plume.cosmetic.CosmeticSlot;
import net.dodogang.plume.cosmetic.client.gui.screen.ingame.CosmeticsScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CosmeticSlotButtonWidget extends TexturedButtonWidget {
    private final CosmeticsScreen parent;
    private final CosmeticSlot slot;

    public CosmeticSlotButtonWidget(CosmeticsScreen parent, CosmeticSlot slot, int x, int y, int u, int v, int hoveredVOffset, Identifier texture, PressAction onClick) {
        super(x, y, 16, 16, u, v, hoveredVOffset, texture, 256, 256, onClick);

        this.parent = parent;
        this.slot = slot;
    }

    public CosmeticSlot getSlot() {
        return this.slot;
    }

    @Override
    public boolean isHovered() {
        return super.isHovered() || parent.getSelectedSlot() == this.slot;
    }
}
