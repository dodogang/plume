package net.dodogang.plume.client;

import com.google.common.reflect.Reflection;
import net.dodogang.plume.Plume;
import net.dodogang.plume.donor.client.DonorDataManagerClient;
import net.dodogang.plume.donor.client.cosmetic.config.CosmeticsConfig;
import net.minecraft.util.Identifier;

public final class PlumeClient {
    @SuppressWarnings("UnstableApiUsage")
    public static void initialize() {
        Plume.log("Initializing (CLIENT)");

        Reflection.initialize(
            // load cosmetics manager before config (initialize client cosmetics, then replace them)
            DonorDataManagerClient.class,
            CosmeticsConfig.class,

            PlumeKeyBindings.class
        );

        Plume.log("Initialized (CLIENT)");
    }

    public static Identifier texture(String path) {
        return new Identifier(Plume.MOD_ID, "textures/" + path + ".png");
    }
}
