package net.dodogang.plume.donor.client.cosmetic;

import net.dodogang.plume.client.PlumeClient;
import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.client.cosmetic.model.CapeCosmeticModel;
import net.dodogang.plume.donor.client.cosmetic.model.CosmeticModel;
import net.dodogang.plume.donor.client.cosmetic.model.ElytraCosmeticModel;
import net.dodogang.plume.donor.client.cosmetic.model.melon_mangler.*;
import net.dodogang.plume.donor.client.cosmetic.model.nautilus.*;
import net.dodogang.plume.donor.client.cosmetic.model.vagabond.*;
import net.dodogang.plume.donor.client.cosmetic.render.CosmeticFeatureRenderer;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.donor.cosmetic.Cosmetics;
import net.dodogang.plume.util.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class CosmeticsClient {
    /**
     * Cosmetic model renderer creator registry.
     */
    private static final Map<Cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>>> RENDERER_MAP = new HashMap<>();
    /**
     * Cosmetic model cape registry.
     */
    private static final Map<Cosmetic, CapeCosmeticModel> CAPE_MODEL_MAP = new HashMap<>();
    /**
     * Cosmetic model elytra registry.
     */
    private static final Map<Cosmetic, ElytraCosmeticModel> ELYTRA_MODEL_MAP = new HashMap<>();
    /**
     * Cosmetic ticker registry.
     */
    private static final Map<Cosmetic, CosmeticTicker> TICKER_MAP = new HashMap<>();

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

        registerRenderer(Cosmetics.VAGABOND_HAT, VagabondHoodModel::new, Texture.VAGABOND);
        registerRenderer(Cosmetics.VAGABOND_BACK, VagabondSackModel::new, Texture.VAGABOND);
        registerRenderer(Cosmetics.VAGABOND_FEET, VagabondBootsModel::new, Texture.VAGABOND);

        registerCapeElytraModel(Cosmetics.VAGABOND_BACK, VagabondCapeModel::new, VagabondElytraModel::new);

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

    public static void registerCapeElytraModel(Cosmetic cosmetic, Supplier<CapeCosmeticModel> cape, Supplier<ElytraCosmeticModel> elytra) {
        CAPE_MODEL_MAP.put(cosmetic, cape.get());
        ELYTRA_MODEL_MAP.put(cosmetic, elytra.get());
    }

    public static void registerTicker(Cosmetic cosmetic, CosmeticTicker ticker) {
        TICKER_MAP.put(cosmetic, ticker);
    }

    public static void cancelCapeElytraRender(LivingEntity entity, CallbackInfo ci) {
        DonorData data = DonorDataManager.get(Util.parseStringUUID(entity.getUuid()));
        Cosmetic cosmetic = data.getSelectedCosmetics().get(CosmeticSlot.BACK);
        if (cosmetic != null && !CosmeticsClient.getCapeModels().containsKey(cosmetic) && !data.getConfig(DonorData.ConfigOptions.BOOL_RENDER_CAPES_AND_ELYTRAS).getAsBoolean()) {
            ci.cancel();
        }
    }

    public static Map<Cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>>> getRenderers() {
        return RENDERER_MAP;
    }
    public static Map<Cosmetic, CapeCosmeticModel> getCapeModels() {
        return CAPE_MODEL_MAP;
    }
    public static Map<Cosmetic, ElytraCosmeticModel> getElytraModels() {
        return ELYTRA_MODEL_MAP;
    }
    public static Map<Cosmetic, CosmeticTicker> getTickers() {
        return TICKER_MAP;
    }

    public static class Texture {
        public static final Identifier MELON_MANGLER    = texture("melon_mangler");
        public static final Identifier NAUTILUS         = texture("nautilus");
        public static final Identifier YELLOW_NAUTILUS  = texture("yellow_nautilus");
        public static final Identifier ORANGE_NAUTILUS  = texture("orange_nautilus");
        public static final Identifier VAGABOND         = texture("vagabond");

        private static Identifier texture(String path) {
            return PlumeClient.texture("cosmetic/" + path);
        }
    }
}
