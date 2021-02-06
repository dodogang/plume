package net.dodogang.plume.forge;

import net.dodogang.plume.forge.client.PlumeClientForge;
import net.dodogang.plume.Plume;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(Plume.MOD_ID)
public class PlumeForge {
    public PlumeForge() {
        Plume.init();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> PlumeClientForge::new);
    }
}
