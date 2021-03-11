package net.dodogang.plume.ash.registry;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

public abstract class RegistryBatch<T> {
    protected final RegistryKey<Registry<T>> registryKey;
    protected final String modId;

    protected boolean registered;

    protected RegistryBatch(RegistryKey<Registry<T>> registryKey, String modId) {
        this.registryKey = registryKey;
        this.modId = modId;

        this.registered = false;
    }

    /**
     * Creates a BatchedRegister with a specific registry key.
     *
     * @param registryKey the registry key found in {@link Registry}
     * @param modId the mod's modId
     * @param <T> The type of object that will be registered.
     * @return a BatchedRegister
     */
    @ExpectPlatform
    public static <T> RegistryBatch<T> create(RegistryKey<Registry<T>> registryKey, String modId) {
        throw new AssertionError();
    }

    /**
     * Adds an object to the batch with the given name.
     *
     * @param name the name of the object to be combined with the modId to make the id
     * @param object the object to be registered
     * @return a {@link RegistrySupplier} containing the id of the object
     */
    public abstract <V extends T> RegistrySupplier<V> add(String name, V object);

    /**
     * Registers all the objects in the registry batch. Should only be called
     * once after all objects have been added to the batch.
     */
    public void register() {
        if (registered) {
            throw new IllegalStateException("Attempted to register BatchRegister of '" + registryKey + "' twice.");
        }
        registered = true;
        registerImpl();
    }
    
    protected abstract void registerImpl();

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Nullable
    protected static <T> Registry<T> findRegistryByKey(RegistryKey<Registry<T>> key) {
        return Registry.REGISTRIES.get((RegistryKey)key);
    }
}
