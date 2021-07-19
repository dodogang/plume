package net.dodogang.plume.ash.fabric;

import net.dodogang.plume.ash.Platform;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;

@ApiStatus.Internal
public final class EnvironmentImpl {
    public static boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    /**
     * Gets the platform during runtime.
     * Quilt will be supported in the future.
     *
     * @return the runtime platform
     */
    public static Platform getPlatform() {
        return Platform.FABRIC;
    }

    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
