package net.dodogang.plume.client.fabric;

import net.dodogang.plume.client.PlumeClient;
import net.fabricmc.api.ClientModInitializer;

public final class PlumeClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlumeClient.initialize();
    }
}
