package net.dodogang.plume.cosmetic.client;

import com.google.common.collect.Lists;
import net.dodogang.plume.client.PlumeClient;
import net.dodogang.plume.cosmetic.*;
import net.dodogang.plume.cosmetic.client.model.CosmeticModel;
import net.dodogang.plume.cosmetic.client.model.melon_mangler.*;
import net.dodogang.plume.cosmetic.client.render.CosmeticFeatureRenderer;
import net.dodogang.plume.util.PlayerUUID;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class CosmeticsManagerClient {
    /**
     * All available cosmetics.
     */
    private static final List<Cosmetic> AVAILABLE = new ArrayList<>();

    /**
     * Cosmetic model renderer creator registry.
     */
    private static final Map<Cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>>> RENDERER_MAP = new HashMap<>();
    /**
     * Cosmetic ticker registry.
     */
    private static final Map<Cosmetic, CosmeticTicker> TICKER_MAP = new HashMap<>();

    static {
        addAvailable(Cosmetics.MELON_MANGLER_HAT, Cosmetics.MELON_MANGLER_MASK, Cosmetics.MELON_MANGLER_CHEST, Cosmetics.MELON_MANGLER_BACK, Cosmetics.MELON_MANGLER_HAND, Cosmetics.MELON_MANGLER_FEET, Cosmetics.MELON_MANGLER_TICKER);

        registerRenderer(Cosmetics.MELON_MANGLER_HAT, MelonManglerHatModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_MASK, MelonManglerMoustacheModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_CHEST, MelonManglerRobesModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_BACK, MelonManglerSackModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_HAND, MelonManglerGlovesModel::new, Texture.MELON_MANGLER);
        registerRenderer(Cosmetics.MELON_MANGLER_FEET, MelonManglerBootsModel::new, Texture.MELON_MANGLER);

        registerTicker(Cosmetics.MELON_MANGLER_TICKER, (world, player) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (!client.options.getPerspective().isFirstPerson() || client.player != player) {
                player.world.addParticle(new DustParticleEffect(59f / 255, 78f / 255, 97f / 255, 1.0f), player.getParticleX(0.7d), player.getRandomBodyY(), player.getParticleZ(0.7d), 0.075d, 0.075d, 0.075d);
            }
        });
    }

    // TODO networking + saved config
    public static void setCosmetic(Cosmetic cosmetic) {
        CosmeticPlayerData data = CosmeticsManager.getLocalData(PlayerUUID.$CLIENT);
        if (data != null) {
            List<Cosmetic> cosmetics = Lists.newArrayList(data.getCosmetics().values());
            cosmetics.removeIf(c -> c == cosmetic);
            cosmetics.add(cosmetic);

            CosmeticsManager.putLocalData(CosmeticPlayerData.of(PlayerUUID.$CLIENT, cosmetics.toArray(new Cosmetic[]{})));
        } else {
            CosmeticsManager.putLocalData(CosmeticPlayerData.of(PlayerUUID.$CLIENT, cosmetic));
        }
    }
    public static void clearCosmeticSlot(CosmeticSlot slot) {
        CosmeticPlayerData data = CosmeticsManager.getLocalData(PlayerUUID.$CLIENT);
        if (data != null) {
            List<Cosmetic> cosmetics = Lists.newArrayList(data.getCosmetics().values());
            cosmetics.removeIf(c -> c.slot == slot);

            CosmeticsManager.putLocalData(CosmeticPlayerData.of(PlayerUUID.$CLIENT, cosmetics.toArray(new Cosmetic[]{})));
        } else {
            CosmeticsManager.putLocalData(CosmeticPlayerData.of(PlayerUUID.$CLIENT));
        }
    }

    public static void registerRenderer(Cosmetic cosmetic, Function<PlayerEntityRenderer, CosmeticModel> model, Identifier texture) {
        CosmeticsManagerClient.registerRenderer(cosmetic, (renderer, ctx) -> new CosmeticFeatureRenderer<>(renderer, cosmetic, ctx, model.apply(renderer), texture));
    }
    public static void registerRenderer(Cosmetic cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>> ctx) {
        CosmeticsManagerClient.RENDERER_MAP.put(cosmetic, ctx);
    }

    public static void registerTicker(Cosmetic cosmetic, CosmeticTicker ticker) {
        CosmeticsManagerClient.TICKER_MAP.put(cosmetic, ticker);
    }

    public static void cancelArmorRender(LivingEntity entity, EquipmentSlot equipmentSlot, CallbackInfo ci) {
        CosmeticPlayerData cosmetics = CosmeticsManager.getLocalData(Util.parseStringUUID(entity.getUuid()));
        if (cosmetics != null) {
            for (CosmeticSlot slot : cosmetics.getCosmetics().keySet()) {
                if (slot.getArmorRenderCancellers().contains(equipmentSlot)) {
                    ci.cancel();
                    return;
                }
            }
        }
    }
    public static void cancelCapeRender(LivingEntity entity, CallbackInfo ci) {
        CosmeticPlayerData cosmetics = CosmeticsManager.getLocalData(Util.parseStringUUID(entity.getUuid()));
        if (cosmetics != null) {
            if (cosmetics.getCosmetics().containsKey(CosmeticSlot.BACK)) {
                ci.cancel();
            }
        }
    }

    public static void addAvailable(Cosmetic... cosmetics) {
        CosmeticsManagerClient.AVAILABLE.removeAll(Arrays.asList(cosmetics));
        CosmeticsManagerClient.AVAILABLE.addAll(Arrays.asList(cosmetics));
    }
    public static List<Cosmetic> getAvailable() {
        return CosmeticsManagerClient.AVAILABLE;
    }

    public static Map<Cosmetic, BiFunction<PlayerEntityRenderer, FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>, CosmeticFeatureRenderer<?>>> getRenderers() {
        return CosmeticsManagerClient.RENDERER_MAP;
    }
    public static Map<Cosmetic, CosmeticTicker> getTickers() {
        return CosmeticsManagerClient.TICKER_MAP;
    }

    private static class Texture {
        private static final Identifier MELON_MANGLER = texture("melon_mangler");

        private static Identifier texture(String path) {
            return PlumeClient.texture("cosmetic/" + path);
        }
    }
}
