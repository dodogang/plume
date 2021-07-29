package net.dodogang.plume.donor.client.cosmetic.gui.widget;

import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.client.DonorDataManagerClient;
import net.dodogang.plume.donor.client.cosmetic.gui.screen.ingame.CosmeticsScreen;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

@Environment(EnvType.CLIENT)
public class CosmeticButtonWidget extends CosmeticScreenButtonWidget implements ChangingCosmeticButtonWidget {
    private final Cosmetic cosmetic;

    public CosmeticButtonWidget(Cosmetic cosmetic, int x, int y, PressAction onClick, TooltipSupplier tooltip) {
        super(x, y, 16, 16, 0, 0, 0, cosmetic.getTexture(), 16, 16, onClick, tooltip, LiteralText.EMPTY);
        this.cosmetic = cosmetic;
    }

    public Cosmetic getCosmetic() {
        return this.cosmetic;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
        super.render(matrices, mouseX, mouseY, tickDelta);

        if (!this.isHovered()) {
            this.renderSelectedOverlay(matrices);
        }
    }

    @Override
    public void renderToolTip(MatrixStack matrices, int mouseX, int mouseY) {
        this.renderSelectedOverlay(matrices);
        super.renderToolTip(matrices, mouseX, mouseY);
    }

    protected void renderSelectedOverlay(MatrixStack matrices) {
        DonorData data = DonorDataManagerClient.getOwn();
        if (data.getSelectedCosmetics().containsValue(this.cosmetic)) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(CosmeticsScreen.TEXTURE_SELECTED);
            DrawableHelper.drawTexture(matrices, this.x, this.y, 0, 0, 16, 16, 16, 16);
        }
    }
}