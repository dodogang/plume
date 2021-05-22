package net.dodogang.plume.client.cosmetic;

import net.dodogang.plume.client.PlumeClient;
import net.dodogang.plume.client.model.cosmetic.CosmeticModel;
import net.dodogang.plume.client.model.cosmetic.melon_mangler.MelonManglerHatModel;
import net.dodogang.plume.client.model.cosmetic.melon_mangler.MelonManglerMoustacheModel;
import net.dodogang.plume.client.model.cosmetic.melon_mangler.MelonManglerRobesModel;
import net.dodogang.plume.client.render.cosmetic.CosmeticFeatureRenderer;
import net.dodogang.plume.cosmetic.Cosmetic;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.CosmeticSlot;
import net.dodogang.plume.cosmetic.Cosmetics;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class ClientCosmeticManager {
    /**
     * Cached player to data cosmetic map.
     */
    public static final Map<UUID, CosmeticPlayerData> LOCAL_DATA = new HashMap<>();

    /**
     * Cached player to cosmetic to feature renderer map.
     */
    public static final Map<UUID, Map<Cosmetic, FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>>> FEATURES_CACHE = new HashMap<>();

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
        ClientCosmeticManager.registerRenderer(cosmetic, (renderer, ctx) -> new CosmeticFeatureRenderer<>(renderer, cosmetic, ctx, model.apply(renderer), texture));
    }
    public static void registerRenderer(Cosmetic cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>> ctx) {
        ClientCosmeticManager.RENDERER_MAP.put(cosmetic, ctx);
    }

    public static void checkToCancelArmorRender(UUID uuid, ArmorItem item, CallbackInfo ci) {
        CosmeticPlayerData cosmetics = ClientCosmeticManager.LOCAL_DATA.get(uuid);
        if (cosmetics != null) {
            for (CosmeticSlot cosmetic : cosmetics.getCosmetics().keySet()) {
                if (cosmetic.getSlotsToCancelRender().contains(item.getSlotType())) {
                    ci.cancel();
                    break;
                }
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
