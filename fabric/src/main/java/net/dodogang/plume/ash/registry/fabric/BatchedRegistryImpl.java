package net.dodogang.plume.ash.registry.fabric;

import net.dodogang.plume.ash.registry.BatchedRegistry;
import net.dodogang.plume.ash.registry.RegistrySupplier;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class BatchedRegistryImpl<T> extends BatchedRegistry<T> {
    private final Registry<T> registry;

    public BatchedRegistryImpl(RegistryKey<Registry<T>> registryKey, String modId) {
        super(registryKey, modId);
        this.registry = findRegistryByKey(registryKey);
    }

    /**
     * Creates a BatchedRegister with a specific registry key.
     *
     * @param registryKey the registry key found in {@link Registry}
     * @param modId the mod's modid
     * @param <T> The type of object that will be registered.
     * @return a BatchedRegister
     */
    public static <T> BatchedRegistry<T> create(RegistryKey<Registry<T>> registryKey, String modId) {
        return new BatchedRegistryImpl<>(registryKey, modId);
    }

    /**
     * Adds an object to the batch with the given name.
     *
     * @param name the name of the object to be combined with the modId to make the id
     * @param object the object to be registered
     * @return a {@link RegistrySupplier} containing the id of the object
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <V extends T> RegistrySupplier<V> add(String name, V object) {
        Identifier id = new Identifier(modId, name);
        Registry.register(registry, id, object);
        return new RegistrySupplier(id, registry, object);
    }

    @Override
    protected void registerImpl() {
        // nop - not necessary on fabric because we can just directly register to vanilla's registry lol
    }
}
