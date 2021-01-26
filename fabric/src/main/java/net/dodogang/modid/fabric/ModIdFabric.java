package net.dodogang.modid.fabric;

import net.dodogang.modid.ModId;
import net.fabricmc.api.ModInitializer;

public class ModIdFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModId.init();
    }
}
