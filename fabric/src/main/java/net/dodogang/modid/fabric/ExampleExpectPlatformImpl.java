package net.dodogang.modid.fabric;

import net.dodogang.modid.ExampleExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class ExampleExpectPlatformImpl {
    /**
     * This is our actual method to {@link ExampleExpectPlatform#getConfigDirectory()}.
     */
    public static File getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir().toFile();
    }
}
