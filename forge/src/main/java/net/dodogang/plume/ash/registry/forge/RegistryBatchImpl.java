package net.dodogang.plume.ash.registry.forge;

import net.dodogang.plume.ash.forge.ModEventBus;
import net.dodogang.plume.ash.registry.RegistryBatch;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class RegistryBatchImpl<T extends IForgeRegistryEntry<T>> extends RegistryBatch<T> {
    private final DeferredRegister<T> deferredRegister;

    public RegistryBatchImpl(RegistryKey<Registry<T>> registryKey, String modId) {
        super(registryKey, modId);
        this.deferredRegister = DeferredRegister.create(RegistryManager.ACTIVE.getRegistry(registryKey), modId);
    }

    /**
     * Creates a BatchedRegister with a specific registry key.
     *
     * @param registryKey the registry key found in {@link Registry}
     * @param modId the mod's modid
     * @param <T> The type of object that will be registered.
     * @return a BatchedRegister
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> RegistryBatch<T> create(RegistryKey<Registry<T>> registryKey, String modId) {
        return new RegistryBatchImpl(registryKey, modId);
    }

    /**
     * Adds an object to the batch with the given name.
     *
     * @param name the name of the object to be combined with the modId to make the id
     * @param object the object to be registered
     * @return the registered object
     */
    @Override
    public <V extends T> V add(String name, V object) {
        deferredRegister.register(name, () -> object);
        return object;
    }

    @Override
    protected void registerImpl() {
        IEventBus modEventBus = ModEventBus.getModEventBus(modId).orElseThrow(() -> new IllegalStateException(
                "Attempted to register BatchedRegister before registering a ModEventBus for modid '" + modId + "'.")
        );
        deferredRegister.register(modEventBus);
    }
}
