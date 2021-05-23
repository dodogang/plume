package net.dodogang.plume.cosmetic.client;

import net.dodogang.plume.client.PlumeClient;
import net.dodogang.plume.cosmetic.*;
import net.dodogang.plume.cosmetic.client.model.CosmeticModel;
import net.dodogang.plume.cosmetic.client.model.melon_mangler.MelonManglerHatModel;
import net.dodogang.plume.cosmetic.client.model.melon_mangler.MelonManglerMoustacheModel;
import net.dodogang.plume.cosmetic.client.model.melon_mangler.MelonManglerRobesModel;
import net.dodogang.plume.cosmetic.client.render.CosmeticFeatureRenderer;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class CosmeticsManagerClient {
    /**
     * Cosmetic model renderer creator registry.
     */
    public static final Map<Cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>>> RENDERER_MAP = new HashMap<>();

    static {
        registerRenderer(Cosmetics.MELON_MANGLER_HAT, MelonManglerHatModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_MASK, MelonManglerMoustacheModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_CHEST, MelonManglerRobesModel::new, Texture.MELON_MANGLER);
    }

    public static void registerRenderer(Cosmetic cosmetic, Function<PlayerEntityRenderer, CosmeticModel> model, Identifier texture) {
        CosmeticsManagerClient.registerRenderer(cosmetic, (renderer, ctx) -> new CosmeticFeatureRenderer<>(renderer, cosmetic, ctx, model.apply(renderer), texture));
    }
    public static void registerRenderer(Cosmetic cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>> ctx) {
        CosmeticsManagerClient.RENDERER_MAP.put(cosmetic, ctx);
    }

    public static void cancelArmorRender(UUID uuid, EquipmentSlot equipmentSlot, CallbackInfo ci) {
        CosmeticPlayerData cosmetics = CosmeticsManager.LOCAL_DATA.get(Util.parseStringUUID(uuid));
        if (cosmetics != null) {
            for (CosmeticSlot slot : cosmetics.getCosmetics().keySet()) {
                if (slot.getArmorRenderCancellers().contains(equipmentSlot)) {
                    ci.cancel();
                    return;
                }
            }
        }
    }
    public static void cancelCapeRender(UUID uuid, CallbackInfo ci) {
        CosmeticPlayerData cosmetics = CosmeticsManager.LOCAL_DATA.get(Util.parseStringUUID(uuid));
        if (cosmetics != null) {
            if (cosmetics.getCosmetics().containsKey(CosmeticSlot.BACK)) {
                ci.cancel();
            }
        }
    }

    private static class Texture {
        private static final Identifier MELON_MANGLER = texture("melon_mangler");

        private static Identifier texture(String path) {
            return PlumeClient.texture("cosmetic/" + path);
        }
    }
}
