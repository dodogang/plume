package net.dodogang.plume.donor.client.cosmetic.gui.screen.ingame;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dodogang.plume.Plume;
import net.dodogang.plume.client.PlumeClient;
import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.client.DonorDataManagerClient;
import net.dodogang.plume.donor.client.cosmetic.config.CosmeticsConfig;
import net.dodogang.plume.donor.client.cosmetic.gui.widget.*;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.donor.cosmetic.Cosmetics;
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
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public class CosmeticsScreen extends Screen {
    public static final Identifier          TEXTURE                         = PlumeClient.texture("gui/cosmetics/background");
    public static final Identifier          TEXTURE_SELECTED                = PlumeClient.texture("gui/cosmetics/selected");
    public static final Identifier          TEXTURE_LOCKED                  = PlumeClient.texture("gui/cosmetics/locked");
    public static final Identifier          TEXTURE_CLEAR_SLOT              = PlumeClient.texture("gui/cosmetics/clear_slot");
    public static final Identifier          TEXTURE_BACK_CONFIG             = PlumeClient.texture("gui/cosmetics/back_config");
    public static final Identifier          TEXTURE_BACK_CONFIG_ON          = PlumeClient.texture("gui/cosmetics/back_config_on");
    public static final Identifier          TEXTURE_BACK_CONFIG_HOVER       = PlumeClient.texture("gui/cosmetics/back_config_hover");
    public static final int                 BACKGROUND_WIDTH                = 199;
    public static final int                 BACKGROUND_HEIGHT               = 190;

    public static final TranslatableText    TEXT_TITLE                      = new TranslatableText("screen." + new Identifier(Plume.MOD_ID, "cosmetics"));
    public static final TranslatableText    TEXT_NO_SELECTED_SLOT           = new TranslatableText("screen." + new Identifier(Plume.MOD_ID, "cosmetics.no_selected_slot"));
    public static final TranslatableText    TEXT_CLEAR_SLOT                 = new TranslatableText("screen." + new Identifier(Plume.MOD_ID, "cosmetics.clear_cosmetic_slot"));
    public static final TranslatableText    TEXT_CLEAR_SLOT_ALL             = (TranslatableText) new TranslatableText("screen." + new Identifier(Plume.MOD_ID, "cosmetics.clear_cosmetic_slot_all")).formatted(Formatting.RED);
    public static final TranslatableText    TEXT_BACK_CONFIG                = new TranslatableText("screen." + new Identifier(Plume.MOD_ID, "cosmetics.back_config"));

    private static final List<CosmeticSlot> COSMETIC_SLOTS_ARMOR            = Arrays.stream(CosmeticSlot.values())
                                                                                    .filter(cosmeticSlot -> !cosmeticSlot.getArmorRenderCancellers().isEmpty())
                                                                                    .collect(Collectors.toList());
    private static final List<CosmeticSlot> COSMETIC_SLOTS_NO_ARMOR         = Arrays.stream(CosmeticSlot.values())
                                                                                    .filter(cosmeticSlot -> cosmeticSlot.getArmorRenderCancellers().isEmpty())
                                                                                    .collect(Collectors.toList());

    protected Map<CosmeticSlot, Cosmetic> cosmeticsSelected;
    protected final List<Cosmetic> cosmeticsAvailable = DonorDataManagerClient.getAvailableCosmetics();

    protected final PlayerEntity player;
    protected float mouseX;
    protected float mouseY;
    protected int backgroundOriginX;
    protected int backgroundOriginY;
    protected boolean shiftHeld = false;

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
            // render right slots before left slots in case of tooltip weaving
            this.initCosmeticSlots(COSMETIC_SLOTS_NO_ARMOR, 139, 16);
            this.initCosmeticSlots(COSMETIC_SLOTS_ARMOR, 44, 0);
        }
    }

    @Override
    public void onClose() {
        CosmeticsConfig.save();
        super.onClose();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
        backgroundOriginX = (this.width / 2) - (BACKGROUND_WIDTH / 2);
        backgroundOriginY = (this.height / 2) - (BACKGROUND_HEIGHT / 2);

        this.mouseX = (float) mouseX;
        this.mouseY = (float) mouseY;

        this.cosmeticsSelected = DonorDataManager.get(player.getUuid()).getSelectedCosmetics();

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
                DonorData data = DonorDataManagerClient.getOwn();
                Cosmetic cosmetic = data.getSelectedCosmetics().get(slot);
                if (cosmetic != null) {
                    this.renderTooltip(matrices, new TranslatableText(cosmetic.getTranslationKey()), mouseX, mouseY);
                    return;
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
        if (this.shiftHeld) {
            for (CosmeticSlot slot : CosmeticSlot.values()) {
                DonorDataManagerClient.clearCosmeticSlot(slot);
            }
        } else {
            DonorDataManagerClient.clearCosmeticSlot(this.selectedSlot);
        }

        this.selectedSlot = null;
        this.updateCosmetics();
    }
    private void onCosmeticClick(ButtonWidget widget) {
        Cosmetic cosmetic = ((CosmeticButtonWidget) widget).getCosmetic();
        DonorDataManagerClient.setCosmetic(cosmetic);
        cosmetic.onClick();
    }
    private void onCloakElytraConfigClick(ButtonWidget widget) {
        DonorData data = DonorDataManagerClient.getOwn();
        Map<String, JsonElement> oldConfig = data.getConfig();

        Map<String, JsonElement> config = new HashMap<>(oldConfig);
        JsonElement oldRenderCloaksAndElytras = oldConfig.get(DonorData.ConfigOptions.BOOL_RENDER_CLOAKS_AND_ELYTRAS);
        config.put(DonorData.ConfigOptions.BOOL_RENDER_CLOAKS_AND_ELYTRAS, new JsonPrimitive(oldRenderCloaksAndElytras == null || !oldRenderCloaksAndElytras.getAsBoolean()));

        data.setConfig(ImmutableMap.copyOf(config));
    }

    public static final int ROW_LENGTH    = 7;
    public static final int COSMETIC_SIZE = 18;

    public void updateCosmetics() {
        // reinitialise slot-specific buttons
        this.buttons.removeIf(w -> w instanceof ChangingCosmeticButtonWidget);
        this.children.removeIf(w -> w instanceof ChangingCosmeticButtonWidget);

        if (this.selectedSlot != null) {
            this.addButton(new ClearCosmeticSlotButtonWidget((this.width / 2) + 70, (this.height / 2) + 36, 16, 16, 0, 0, 0, TEXTURE_CLEAR_SLOT, 16, 16, this::onClearSlotClick, (w, matrices, mouseX, mouseY) -> this.renderTooltip(matrices, this.shiftHeld ? TEXT_CLEAR_SLOT_ALL : TEXT_CLEAR_SLOT, mouseX, mouseY), LiteralText.EMPTY));

            if (this.selectedSlot == CosmeticSlot.BACK) {
                this.addButton(new CosmeticCloakAndElytraConfigButtonWidget((this.width / 2) - 86, (this.height / 2) + 36, 16, 16, 0, 0, 0, TEXTURE_BACK_CONFIG, 16, 16, this::onCloakElytraConfigClick, (w, matrices, mouseX, mouseY) -> this.renderTooltip(matrices, TEXT_BACK_CONFIG, mouseX, mouseY), LiteralText.EMPTY));
            }
        }

        // add cosmetics
        this.cosmeticsDisplayed.clear();
        this.cosmeticsDisplayed.addAll(Cosmetics.ALL);
        this.cosmeticsDisplayed.removeIf(cosmetic -> cosmetic.getSlot() != this.selectedSlot);

        int row = -1;
        for (int i = 0; i < this.cosmeticsDisplayed.size() && i < 21; i++) {
            if (i % ROW_LENGTH == 0) row++;

            Cosmetic cosmetic = this.cosmeticsDisplayed.get(i);
            int x = (this.width / 2)  - (BACKGROUND_WIDTH  / 2) + 37 + (i * COSMETIC_SIZE) - (row * COSMETIC_SIZE * ROW_LENGTH);
            int y = (this.height / 2) - (BACKGROUND_HEIGHT / 2) + 113 + (row * COSMETIC_SIZE);
            this.addButton(new CosmeticButtonWidget(cosmetic,  this.cosmeticsAvailable.contains(cosmetic), x, y, this::onCosmeticClick, (w, matrices, mouseX, mouseY) -> {
                TranslatableText title = new TranslatableText(cosmetic.getTranslationKey());
                this.renderTooltip(matrices, cosmetic.hasDescription() ? Arrays.asList(title, new TranslatableText(cosmetic.getDescriptionKey()).formatted(Formatting.GRAY)) : Collections.singletonList(title), mouseX, mouseY);
            }));
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

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_LEFT_SHIFT || keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            this.shiftHeld = true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void resize(MinecraftClient client, int i, int j) {
        CosmeticSlot selectedSlot = this.selectedSlot;
        super.resize(client, i, j);

        this.selectedSlot = selectedSlot;
        this.updateCosmetics();
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_LEFT_SHIFT || keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            this.shiftHeld = false;
        }

        return super.keyReleased(keyCode, scanCode, modifiers);
    }
}
