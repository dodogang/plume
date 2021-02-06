package net.dodogang.plume.fabric.client;

import net.dodogang.plume.client.PlumeClient;
import net.fabricmc.api.ClientModInitializer;

public class PlumeClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlumeClient.init();
    }
}
