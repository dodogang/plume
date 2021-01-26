package net.dodogang.modid.forge;

import me.shedaniel.architectury.platform.forge.EventBuses;
import net.dodogang.modid.ModId;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModId.MOD_ID)
public class ModIdForge {
    public ModIdForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ModId.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ModId.init();
    }
}
