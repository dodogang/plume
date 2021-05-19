package net.dodogang.plume.ash.forge;

import net.dodogang.plume.ash.Platform;
import net.minecraftforge.fml.loading.FMLLoader;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class EnvironmentImpl {
    public static boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
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
}
