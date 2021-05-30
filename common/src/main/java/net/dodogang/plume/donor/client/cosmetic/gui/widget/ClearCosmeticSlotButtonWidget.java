package net.dodogang.plume.donor.client.cosmetic.gui.widget;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ClearCosmeticSlotButtonWidget extends CosmeticScreenButtonWidget implements ChangingCosmeticButtonWidget {
    public ClearCosmeticSlotButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, PressAction onClick, TooltipSupplier tooltip, Text message) {
        super(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, onClick, tooltip, message);
    }
}
