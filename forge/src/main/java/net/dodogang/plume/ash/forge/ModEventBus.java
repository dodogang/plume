package net.dodogang.plume.ash.forge;

import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Optional;

/**
 * In order for ash to handle forge events on the mod event bus ash must have
 * access to said event bus. Forge doesn't give anyway for ash to get any mod's
 * event bus, so any mod using ash must register it here for ash's use.
 *
 * In a mod's forge module immediately after being constructed:
 * ModEventBus.registerModEventBus("mod_id", FMLJavaModLoadingContext.get().getModEventBus());
 */
public final class ModEventBus {
    private ModEventBus() {}

    private static final HashMap<String, IEventBus> MOD_EVENT_BUSES = new HashMap<>();

    /**
     * Register the mod's forge mod event bus to ash.
     *
     * @param modId the mod's modid
     * @param bus FMLJavaModLoadingContext.get().getModEventBus()
     */
    public static void registerModEventBus(String modId, IEventBus bus) {
        IEventBus previousBus = MOD_EVENT_BUSES.put(modId, bus);
        if (previousBus != null) {
            throw new IllegalStateException("Attempted to register a mod event bus for modid '" + modId + "' twice.");
        }
    }

    public static Optional<IEventBus> getModEventBus(String modId) {
        return Optional.ofNullable(MOD_EVENT_BUSES.get(modId));
    }

    public static IEventBus getModEventBusOrThrow(String modId) {
        return getModEventBus(modId).orElseThrow(
                () -> new IllegalStateException("Mod Event Bus for modid '" + modId + "' has not been registered.")
        );
    }
}
