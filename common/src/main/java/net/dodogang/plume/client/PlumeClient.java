package net.dodogang.plume.client;

import net.dodogang.plume.Plume;
import net.dodogang.plume.client.cosmetic.ClientCosmeticsManager;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.Cosmetics;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public final class PlumeClient {
    public static void initialize() {
        ClientCosmeticsManager.PLAYER_DATA.put(
            MinecraftClient.getInstance().getSession().getProfile().getId(), CosmeticPlayerData.of(MinecraftClient.getInstance().getSession().getProfile().getId(), Cosmetics.MELON_MANGLER_HAT, Cosmetics.MELON_MANGLER_MASK, Cosmetics.PARTICLE)
        );
    }

    public static Identifier texture(String path) {
        return new Identifier(Plume.MOD_ID, "textures/" + path + ".png");
    }
}
