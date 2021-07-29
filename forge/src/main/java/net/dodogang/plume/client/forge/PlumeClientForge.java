package net.dodogang.plume.client.forge;

import net.dodogang.plume.client.PlumeClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class PlumeClientForge {
    public PlumeClientForge() {
        PlumeClient.initialize();
    }
}
