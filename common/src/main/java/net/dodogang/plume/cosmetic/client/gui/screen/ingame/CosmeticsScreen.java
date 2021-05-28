package net.dodogang.plume.cosmetic.client.gui.screen.ingame;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dodogang.plume.Plume;
import net.dodogang.plume.client.PlumeClient;
import net.dodogang.plume.cosmetic.Cosmetic;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.CosmeticSlot;
import net.dodogang.plume.cosmetic.CosmeticsManager;
import net.dodogang.plume.cosmetic.client.CosmeticsManagerClient;
import net.dodogang.plume.cosmetic.client.gui.widget.CosmeticButtonWidget;
import net.dodogang.plume.cosmetic.client.gui.widget.CosmeticClearButtonWidget;
import net.dodogang.plume.cosmetic.client.gui.widget.CosmeticSlotButtonWidget;
import net.dodogang.plume.util.PlayerUUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public class CosmeticsScreen extends Screen {
    public static final Identifier          TEXTURE                 = PlumeClient.texture("gui/cosmetics/background");
    public static final Identifier          TEXTURE_SELECTED        = PlumeClient.texture("gui/cosmetics/selected");
    public static final Identifier          TEXTURE_CLEAR_SLOT      = PlumeClient.texture("gui/cosmetics/clear_slot");
    public static final int                 BACKGROUND_WIDTH        = 199;
    public static final int                 BACKGROUND_HEIGHT       = 190;

    public static final TranslatableText    TEXT_TITLE              = new TranslatableText("screen." + new Identifier(Plume.MOD_ID, "cosmetics"));
    public static final TranslatableText    TEXT_NO_SELECTED_SLOT   = new TranslatableText("screen." + new Identifier(Plume.MOD_ID, "cosmetics.no_selected_slot"));
    public static final TranslatableText    TEXT_CLEAR_SLOT         = new TranslatableText("screen." + new Identifier(Plume.MOD_ID, "cosmetics.clear_cosmetic_slot"));

    private static final List<CosmeticSlot> COSMETIC_SLOTS_ARMOR    = Arrays.stream(CosmeticSlot.values())
                                                                            .filter(cosmeticSlot -> !cosmeticSlot.getArmorRenderCancellers().isEmpty())
                                                                            .collect(Collectors.toList());
    private static final List<CosmeticSlot> COSMETIC_SLOTS_NO_ARMOR = Arrays.stream(CosmeticSlot.values())
                                                                            .filter(cosmeticSlot -> cosmeticSlot.getArmorRenderCancellers().isEmpty())
                                                                            .collect(Collectors.toList());

    protected Map<CosmeticSlot, Cosmetic> cosmeticsSelected;
    protected final List<Cosmetic> cosmeticsAvailable = CosmeticsManagerClient.getAvailable();

    protected final PlayerEntity player;
    protected float mouseX;
    protected float mouseY;
    protected int backgroundOriginX;
    protected int backgroundOriginY;

    @Nullable protected CosmeticSlot selectedSlot;

    protected int scrollPosition;
    protected final List<Cosmetic> cosmeticsDisplayed = new ArrayList<>();

    public CosmeticsScreen(PlayerEntity player) {
        super(CosmeticsScreen.TEXT_TITLE);
        this.player = player;
    }

    @Override
    protected void init() {
        if (this.client != null) {
            this.initCosmeticSlots(COSMETIC_SLOTS_ARMOR, 44, 0);
            this.initCosmeticSlots(COSMETIC_SLOTS_NO_ARMOR, 139, 16);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
        backgroundOriginX = (this.width / 2) - (BACKGROUND_WIDTH / 2);
        backgroundOriginY = (this.height / 2) - (BACKGROUND_HEIGHT / 2);

        this.mouseX = (float) mouseX;
        this.mouseY = (float) mouseY;

        CosmeticPlayerData cosmetics = CosmeticsManager.getLocalData(player.getUuid());
        this.cosmeticsSelected = cosmetics == null ? new HashMap<>() : cosmetics.getCosmetics();

        if (this.client != null) {
            // ui background
            this.renderBackground(matrices);
            this.client.getTextureManager().bindTexture(CosmeticsScreen.TEXTURE);
            this.drawTexture(matrices, this.backgroundOriginX, this.backgroundOriginY, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

            // text
            DrawableHelper.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, (this.height / 2) - 108, DyeColor.WHITE.getSignColor());
            RenderSystem.disableBlend();
            Text selectedSlotText = this.selectedSlot == null ? TEXT_NO_SELECTED_SLOT : this.selectedSlot.getDisplayText();
            this.textRenderer.draw(matrices, selectedSlotText, (this.width / 2f) - (textRenderer.getWidth(selectedSlotText) / 2f), (this.height / 2f) + 77, 4210752);

            // player renderer

            boolean hasHat = this.cosmeticsSelected.containsKey(CosmeticSlot.HAT);
            int rendererScale = hasHat ? 32 : 42;
            int rendererOffsetY = hasHat ? -5 : -3;

            InventoryScreen.drawEntity(this.width / 2, (this.height / 2) - rendererOffsetY, rendererScale, (this.width / 2f) - this.mouseX + 2, (this.height / 2f) - this.mouseY - (rendererScale * 1.5f) - rendererOffsetY, this.player);
        }

        super.render(matrices, mouseX, mouseY, tickDelta);
    }

    protected void initCosmeticSlots(List<CosmeticSlot> slots, int x, int u) {
        x += (this.width / 2) - (BACKGROUND_WIDTH / 2);
        u += 208;

        for (int i = 0; i < slots.size(); i++) {
            CosmeticSlot slot = slots.get(i);
            int y = (this.height / 2) - (BACKGROUND_HEIGHT / 2) + 23 + (i * 18);
            int v = 16 + (i * 16);

            this.addButton(new CosmeticSlotButtonWidget(this, slot, x, y, u, v, 16 * slots.size(), TEXTURE, this::onSlotClick, (w, matrices, mouseX, mouseY) -> {
                CosmeticPlayerData data = CosmeticsManager.getLocalData(PlayerUUID.$CLIENT);
                if (data != null) {
                    Cosmetic cosmetic = data.getCosmetics().get(slot);
                    if (cosmetic != null) {
                        this.renderTooltip(matrices, new TranslatableText(cosmetic.getTranslationKey()), mouseX, mouseY);
                        return;
                    }
                }

                if (((CosmeticSlotButtonWidget) w).isJustHovered()) {
                    this.renderTooltip(matrices, slot.getDisplayText(), mouseX, mouseY);
                }
            }));
        }
    }

    public void onSlotClick(ButtonWidget widget) {
        this.selectedSlot = ((CosmeticSlotButtonWidget) widget).getSlot();
        this.scrollPosition = 0;
        this.updateCosmetics();
    }

    private void onClearSlotClick(ButtonWidget widget) {
        CosmeticsManagerClient.clearCosmeticSlot(this.selectedSlot);

        this.selectedSlot = null;
        this.updateCosmetics();
    }

    private void onCosmeticClick(ButtonWidget widget) {
        Cosmetic cosmetic = ((CosmeticButtonWidget) widget).getCosmetic();
        CosmeticsManagerClient.setCosmetic(cosmetic);
        cosmetic.onClick();
    }

    public static final int ROW_LENGTH    = 7;
    public static final int COSMETIC_SIZE = 18;

    public void updateCosmetics() {
        this.cosmeticsDisplayed.clear();
        this.cosmeticsDisplayed.addAll(this.cosmeticsAvailable);
        this.cosmeticsDisplayed.removeIf(cosmetic -> cosmetic.slot != this.selectedSlot);

        // reinitialise cosmetic clear button if required
        this.buttons.removeIf(w -> w instanceof CosmeticClearButtonWidget);
        this.children.removeIf(w -> w instanceof CosmeticClearButtonWidget);

        if (this.selectedSlot != null) {
            this.addButton(new CosmeticClearButtonWidget((this.width / 2) + 70, (this.height / 2) + 36, 16, 16, 0, 0, 0, TEXTURE_CLEAR_SLOT, 16, 16, this::onClearSlotClick, (w, matrices, mouseX, mouseY) -> this.renderTooltip(matrices, TEXT_CLEAR_SLOT, mouseX, mouseY), LiteralText.EMPTY));
        }

        // remove current cosmetics
        this.buttons.removeIf(widget -> widget instanceof CosmeticButtonWidget);
        this.children.removeIf(widget -> widget instanceof CosmeticButtonWidget);

        int row = -1;
        for (int i = 0; i < this.cosmeticsDisplayed.size() && i < 21; i++) {
            if (i % ROW_LENGTH == 0) row++;

            Cosmetic cosmetic = this.cosmeticsDisplayed.get(i);
            int x = (this.width / 2)  - (BACKGROUND_WIDTH  / 2) + 37 + (i * COSMETIC_SIZE) - (row * COSMETIC_SIZE * ROW_LENGTH);
            int y = (this.height / 2) - (BACKGROUND_HEIGHT / 2) + 113 + (row * COSMETIC_SIZE);
            this.addButton(new CosmeticButtonWidget(cosmetic, x, y, this::onCosmeticClick, (w, matrices, mouseX, mouseY) -> this.renderTooltip(matrices, new TranslatableText(((CosmeticButtonWidget) w).getCosmetic().getTranslationKey()), mouseX, mouseY)));
        }
    }

    public static void cancelOtherRenders(MinecraftClient client, CallbackInfo ci) {
        if (client != null && client.currentScreen instanceof CosmeticsScreen) {
            ci.cancel();
        }
    }

    public CosmeticSlot getSelectedSlot() {
        return this.selectedSlot;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
