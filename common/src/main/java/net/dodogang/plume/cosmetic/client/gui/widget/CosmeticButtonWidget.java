package net.dodogang.plume.cosmetic.client.gui.widget;

import net.dodogang.plume.cosmetic.Cosmetic;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
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

@Environment(EnvType.CLIENT)
public class CosmeticButtonWidget extends TexturedButtonWidget {
    private final Cosmetic cosmetic;

    public CosmeticButtonWidget(Cosmetic cosmetic, int x, int y, PressAction onClick, TooltipSupplier tooltip) {
        super(x, y, 16, 16, 0, 0, 0, cosmetic.texture, 16, 16, onClick, tooltip, LiteralText.EMPTY);
        this.cosmetic = cosmetic;
    }

    public Cosmetic getCosmetic() {
        return this.cosmetic;
    }

    @Override
    public void render(MatrixStack matrices, int i, int j, float f) {
        super.render(matrices, i, j, f);

        CosmeticPlayerData data = CosmeticsManager.getLocalData(PlayerUUID.$CLIENT);
        if (data != null && data.getCosmetics().containsValue(this.cosmetic)) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(CosmeticsScreen.TEXTURE_ICONS);
            DrawableHelper.drawTexture(matrices, this.x, this.y, 0, 0, 16, 16, 32, 32);
        }
    }
}
