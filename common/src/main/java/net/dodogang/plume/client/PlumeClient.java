package net.dodogang.plume.client;

import com.google.common.reflect.Reflection;
import net.dodogang.plume.Plume;
import net.dodogang.plume.cosmetic.client.CosmeticsManagerClient;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.Cosmetics;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public final class PlumeClient {
    @SuppressWarnings("UnstableApiUsage")
    public static void initialize() {
        // gives the client player whatever cosmetics, testing TODO
        CosmeticsManagerClient.LOCAL_DATA.put(
            MinecraftClient.getInstance().getSession().getProfile().getId(), CosmeticPlayerData.of(MinecraftClient.getInstance().getSession().getProfile().getId(), Cosmetics.MELON_MANGLER_HAT, Cosmetics.MELON_MANGLER_MASK, Cosmetics.MELON_MANGLER_CHEST, Cosmetics.AURA)
        );

        Reflection.initialize(
            CosmeticsManagerClient.class
        );
    }

    public static Identifier texture(String path) {
        return new Identifier(Plume.MOD_ID, "textures/" + path + ".png");
    }
}
