package net.dodogang.plume.client;

import net.dodogang.plume.Plume;
import net.minecraft.util.Identifier;

public final class PlumeClient {
    public static void initialize() {
        Plume.log("Initializing (CLIENT)");

        //

        Plume.log("Initialized (CLIENT)");
    }

    public static Identifier texture(String path) {
        return new Identifier(Plume.MOD_ID, "textures/" + path + ".png");
    }
}
