package net.dodogang.plume.donor.client.cosmetic;

import net.dodogang.plume.client.PlumeClient;
import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.cosmetic.*;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.dodogang.plume.donor.client.cosmetic.model.melon_mangler.*;
import net.dodogang.plume.donor.client.cosmetic.model.nautilus.*;
import net.dodogang.plume.donor.client.cosmetic.render.CosmeticFeatureRenderer;
import net.dodogang.plume.util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CosmeticsClient {
    /**
     * Cosmetic model renderer creator registry.
     */
    static final Map<Cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>>> RENDERER_MAP = new HashMap<>();
    /**
     * Cosmetic ticker registry.
     */
    static final Map<Cosmetic, CosmeticTicker> TICKER_MAP = new HashMap<>();

    static {
        registerRenderer(Cosmetics.MELON_MANGLER_HAT, MelonManglerHatModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_MASK, MelonManglerMoustacheModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_CHEST, MelonManglerRobesModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_BACK, MelonManglerSackModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_HAND, MelonManglerGlovesModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_FEET, MelonManglerBootsModel::new, Texture.MELON_MANGLER);

        registerRenderer(Cosmetics.NAUTILUS_HAT, NautilusHatModel::new, Texture.NAUTILUS);
        registerRenderer(Cosmetics.NAUTILUS_BACK, NautilusBackpackModel::new, Texture.NAUTILUS);
        registerRenderer(Cosmetics.YELLOW_NAUTILUS_HAT, YellowNautilusHatModel::new, Texture.YELLOW_NAUTILUS);
        registerRenderer(Cosmetics.YELLOW_NAUTILUS_BACK, YellowNautilusBackpackModel::new, Texture.YELLOW_NAUTILUS);
        registerRenderer(Cosmetics.ORANGE_NAUTILUS_HAT, OrangeNautilusHatModel::new, Texture.ORANGE_NAUTILUS);
        registerRenderer(Cosmetics.ORANGE_NAUTILUS_BACK, OrangeNautilusBackpackModel::new, Texture.ORANGE_NAUTILUS);

        registerTicker(Cosmetics.MELON_MANGLER_TICKER, (world, player) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (!client.options.getPerspective().isFirstPerson() || client.player != player) {
                player.world.addParticle(new DustParticleEffect(59f / 255, 78f / 255, 97f / 255, 1.0f), player.getParticleX(0.7d), player.getRandomBodyY(), player.getParticleZ(0.7d), 0.075d, 0.075d, 0.075d);
            }
        });
    }

    public static void registerRenderer(Cosmetic cosmetic, Function<PlayerEntityRenderer, CosmeticModel> model, Identifier texture) {
        registerRenderer(cosmetic, (renderer, ctx) -> new CosmeticFeatureRenderer<>(renderer, cosmetic, ctx, model.apply(renderer), texture));
    }
    public static void registerRenderer(Cosmetic cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>> ctx) {
        RENDERER_MAP.put(cosmetic, ctx);
    }

    public static void registerTicker(Cosmetic cosmetic, CosmeticTicker ticker) {
        TICKER_MAP.put(cosmetic, ticker);
    }

    public static void cancelArmorRender(LivingEntity entity, EquipmentSlot equipmentSlot, CallbackInfo ci) {
        DonorData cosmetics = DonorDataManager.get(Util.parseStringUUID(entity.getUuid()));
        if (cosmetics != null) {
            for (CosmeticSlot slot : cosmetics.getSelectedCosmetics().keySet()) {
                if (slot.getArmorRenderCancellers().contains(equipmentSlot)) {
                    ci.cancel();
                    return;
                }
            }
        }
    }
    public static void cancelCapeRender(LivingEntity entity, CallbackInfo ci) {
        DonorData cosmetics = DonorDataManager.get(Util.parseStringUUID(entity.getUuid()));
        if (cosmetics != null) {
            if (cosmetics.getSelectedCosmetics().containsKey(CosmeticSlot.BACK)) {
                ci.cancel();
            }
        }
    }

    public static Map<Cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>>> getRenderers() {
        return RENDERER_MAP;
    }
    public static Map<Cosmetic, CosmeticTicker> getTickers() {
        return TICKER_MAP;
    }

    static class Texture {
        public static final Identifier MELON_MANGLER    = texture("melon_mangler");
        public static final Identifier NAUTILUS         = texture("nautilus");
        public static final Identifier YELLOW_NAUTILUS  = texture("yellow_nautilus");
        public static final Identifier ORANGE_NAUTILUS  = texture("orange_nautilus");

        private static Identifier texture(String path) {
            return PlumeClient.texture("cosmetic/" + path);
        }
    }
}
