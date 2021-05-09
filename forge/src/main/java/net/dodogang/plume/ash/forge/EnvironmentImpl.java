package net.dodogang.plume.ash.forge;

import net.dodogang.plume.ash.Platform;
import net.minecraftforge.fml.loading.FMLLoader;

public class EnvironmentImpl {
    public static boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    public static Platform getPlatform() {
        return Platform.FABRIC;
    }
}
