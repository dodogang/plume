package net.dodogang.plume.forge;

import net.dodogang.ash.forge.ModEventBus;
import net.dodogang.plume.forge.client.PlumeClientForge;
import net.dodogang.plume.Plume;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Plume.MOD_ID)
public class PlumeForge {
    public PlumeForge() {
        ModEventBus.registerModEventBus(Plume.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        Plume.init();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> PlumeClientForge::new);
    }
}
