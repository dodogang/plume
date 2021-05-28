package net.dodogang.plume.cosmetic.client.gui.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CosmeticClearButtonWidget extends TexturedButtonWidget {
    public CosmeticClearButtonWidget(int i, int j, int k, int l, int m, int n, int o, Identifier identifier, int p, int q, PressAction pressAction, TooltipSupplier tooltipSupplier, Text text) {
        super(i, j, k, l, m, n, o, identifier, p, q, pressAction, tooltipSupplier, text);
    }
}
