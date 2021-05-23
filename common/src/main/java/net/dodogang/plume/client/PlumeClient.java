package net.dodogang.plume.client;

import com.google.common.reflect.Reflection;
import net.dodogang.plume.Plume;
import net.dodogang.plume.cosmetic.client.CosmeticsManagerClient;
import net.minecraft.util.Identifier;

public final class PlumeClient {
    @SuppressWarnings("UnstableApiUsage")
    public static void initialize() {
        Reflection.initialize(
            CosmeticsManagerClient.class
        );
    }

    public static Identifier texture(String path) {
        return new Identifier(Plume.MOD_ID, "textures/" + path + ".png");
    }
}
