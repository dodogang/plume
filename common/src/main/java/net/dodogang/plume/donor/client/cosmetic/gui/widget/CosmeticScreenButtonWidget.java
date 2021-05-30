package net.dodogang.plume.donor.client.cosmetic.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public abstract class CosmeticScreenButtonWidget extends TexturedButtonWidget {
    private final int u;
    private final int v;
    private final int hoveredVOffset;
    private final Identifier texture;
    private final int textureWidth;
    private final int textureHeight;

    public CosmeticScreenButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, PressAction onClick, TooltipSupplier tooltip, Text message) {
        super(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, onClick, tooltip, message);

        this.u = u;
        this.v = v;
        this.hoveredVOffset = hoveredVOffset;
        this.texture = texture;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    @Override
    public void renderButton(MatrixStack matrices, int i, int j, float f) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        minecraftClient.getTextureManager().bindTexture(this.getTexture());
        int v = this.v;
        if (this.isHovered()) {
            v += this.hoveredVOffset;
        }

        RenderSystem.enableDepthTest();
        drawTexture(matrices, this.x, this.y, (float) this.u, (float) v, this.width, this.height, this.textureWidth, this.textureHeight);
        if (this.isHovered()) {
            this.renderToolTip(matrices, i, j);
        }
    }

    public Identifier getTexture() {
        return this.texture;
    }
}
