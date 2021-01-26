package net.dodogang.modid.forge;

import net.dodogang.modid.ExampleExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class ExampleExpectPlatformImpl {
    /**
     * This is our actual method to {@link ExampleExpectPlatform#getConfigDirectory()}.
     */
    public static File getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get().toFile();
    }
}
