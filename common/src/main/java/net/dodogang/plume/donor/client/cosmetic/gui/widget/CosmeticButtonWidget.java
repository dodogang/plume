package net.dodogang.plume.donor.client.cosmetic.gui.widget;

import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.client.DonorDataManagerClient;
import net.dodogang.plume.donor.client.cosmetic.gui.screen.ingame.CosmeticsScreen;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

@Environment(EnvType.CLIENT)
public class CosmeticButtonWidget extends CosmeticScreenButtonWidget implements ChangingCosmeticButtonWidget {
    private final Cosmetic cosmetic;
    private final boolean isAvailable;

    public CosmeticButtonWidget(Cosmetic cosmetic, boolean isAvailable, int x, int y, PressAction onClick, TooltipSupplier tooltip) {
        super(x, y, 16, 16, 0, 0, 0, cosmetic.getTexture(), 16, 16, onClick, tooltip, LiteralText.EMPTY);
        this.cosmetic = cosmetic;
        this.isAvailable = isAvailable;
    }

    public Cosmetic getCosmetic() {
        return this.cosmetic;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
        super.render(matrices, mouseX, mouseY, tickDelta);

        if (!this.isHovered()) {
            this.renderOverlays(matrices);
        }
    }

    @Override
    public void renderToolTip(MatrixStack matrices, int mouseX, int mouseY) {
        this.renderOverlays(matrices);
        super.renderToolTip(matrices, mouseX, mouseY);
    }

    public void renderOverlays(MatrixStack matrices) {
        if (this.isAvailable()) {
            this.renderSelectedOverlay(matrices);
        } else {
            this.renderLockedOverlay(matrices);
        }
    }

    public void renderSelectedOverlay(MatrixStack matrices) {
        DonorData data = DonorDataManagerClient.getOwn();
        if (data.getSelectedCosmetics().containsValue(this.cosmetic)) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(CosmeticsScreen.TEXTURE_SELECTED);
            DrawableHelper.drawTexture(matrices, this.x, this.y, 0, 0, 16, 16, 16, 16);
        }
    }
    public void renderLockedOverlay(MatrixStack matrices) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(CosmeticsScreen.TEXTURE_LOCKED);

        matrices.push();
        matrices.translate(-0.5d, 0.0d, 0.0d); // translate half a pixel to the left to centre
        DrawableHelper.drawTexture(matrices, this.x, this.y, 0, 0, 16, 16, 16, 16);
        matrices.pop();
    }

    @Override
    public void onPress() {
        if (this.isAvailable()) {
            super.onPress();
        }
    }
    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(this.isAvailable() ? SoundEvents.UI_BUTTON_CLICK : SoundEvents.BLOCK_CHEST_LOCKED, 1.0F));
    }
}
