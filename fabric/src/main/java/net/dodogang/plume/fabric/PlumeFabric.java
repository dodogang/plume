package net.dodogang.plume.fabric;

import net.dodogang.plume.Plume;
import net.fabricmc.api.ModInitializer;

public class PlumeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Plume.initialize();
        Plume.setup();
    }
}
