package net.dodogang.plume.client;

import com.google.common.reflect.Reflection;
import net.dodogang.plume.Plume;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public final class PlumeClient {
    @SuppressWarnings("UnstableApiUsage")
    public static void initialize() {
        Plume.log("Initializing (CLIENT)");

        Reflection.initialize(
            PlumeKeyBindings.class
        );

        Plume.log("Initialized (CLIENT)");
    }

    public static Identifier texture(String path) {
        return new Identifier(Plume.MOD_ID, "textures/" + path + ".png");
    }
}
