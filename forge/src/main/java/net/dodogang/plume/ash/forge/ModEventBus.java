package net.dodogang.plume.ash.forge;

import net.minecraftforge.eventbus.api.IEventBus;

import java.util.HashMap;
import java.util.Optional;

public class ModEventBus {
    private static final HashMap<String, IEventBus> MOD_EVENT_BUSES = new HashMap<>();

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
