package net.dodogang.plume.donor.client.cosmetic.gui.widget;

import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.client.DonorDataManagerClient;
import net.dodogang.plume.donor.client.cosmetic.gui.screen.ingame.CosmeticsScreen;
import net.dodogang.plume.util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CosmeticCapeConfigButtonWidget extends CosmeticScreenButtonWidget implements ChangingCosmeticButtonWidget {
    public CosmeticCapeConfigButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, PressAction onClick, TooltipSupplier tooltip, Text message) {
        super(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, onClick, tooltip, message);
    }

    @Override
    public void renderButton(MatrixStack matrices, int i, int j, float f) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(Util.WIDGET_BACKGROUND);
        drawTexture(matrices, this.x - 1, this.y - 1, 0, 0, 18, 18, 18, 18);

        super.renderButton(matrices, i, j, f);
    }

    @Override
    public Identifier getTexture() {
        return DonorDataManagerClient.getOwn().getConfig(DonorData.ConfigOptions.BOOL_RENDER_CAPES_AND_ELYTRAS).getAsBoolean()
            ? CosmeticsScreen.TEXTURE_BACK_CONFIG_ON
            : this.isHovered()
                ? CosmeticsScreen.TEXTURE_BACK_CONFIG_HOVER
                : super.getTexture();
    }
}
