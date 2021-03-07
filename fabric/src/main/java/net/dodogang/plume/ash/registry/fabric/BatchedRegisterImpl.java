package net.dodogang.plume.ash.registry.fabric;

import net.dodogang.plume.ash.registry.BatchedRegister;
import net.dodogang.plume.ash.registry.RegistrySupplier;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class BatchedRegisterImpl<T> extends BatchedRegister<T> {
    private final Registry<T> registry;

    public BatchedRegisterImpl(RegistryKey<Registry<T>> registryKey, String modId) {
        super(registryKey, modId);
        this.registry = findRegistryByKey(registryKey);
    }

    public static <T> BatchedRegister<T> create(RegistryKey<Registry<T>> registryKey, String modId) {
        return new BatchedRegisterImpl<>(registryKey, modId);
    }

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
