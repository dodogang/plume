package net.dodogang.plume.forge;

import net.dodogang.plume.Plume;
import net.dodogang.plume.ash.forge.ModEventBus;
import net.dodogang.plume.ash.registry.forge.FuelRegistryImpl;
import net.dodogang.plume.client.forge.PlumeClientForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Plume.MOD_ID)
public class PlumeForge {
    public PlumeForge() {
        ModEventBus.registerModEventBus(Plume.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        registerForgeEventsForAsh();

        Plume.initialize();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> PlumeClientForge::new);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        Plume.setup();
    }

    private void registerForgeEventsForAsh() {
        MinecraftForge.EVENT_BUS.addListener(FuelRegistryImpl::furnaceFuelBurnTimeHandler);
    }
}
