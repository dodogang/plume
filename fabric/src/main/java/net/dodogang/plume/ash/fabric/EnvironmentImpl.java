package net.dodogang.plume.ash.fabric;

import net.dodogang.plume.ash.Platform;
import net.fabricmc.loader.api.FabricLoader;

public class EnvironmentImpl {
    public static boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static Platform getPlatform() {
        return Platform.FABRIC;
    }
}
