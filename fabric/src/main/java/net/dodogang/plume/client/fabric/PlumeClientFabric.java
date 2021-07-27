package net.dodogang.plume.client.fabric;

import net.dodogang.plume.client.PlumeClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class PlumeClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlumeClient.initialize();
    }
}
