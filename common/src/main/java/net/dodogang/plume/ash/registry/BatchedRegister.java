package net.dodogang.plume.ash.registry;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

public abstract class BatchedRegister<T> {
    protected final RegistryKey<Registry<T>> registryKey;
    protected final String modId;

    protected boolean registered;

    protected BatchedRegister(RegistryKey<Registry<T>> registryKey, String modId) {
        this.registryKey = registryKey;
        this.modId = modId;

        this.registered = false;
    }
    
    @ExpectPlatform
    public static <T> BatchedRegister<T> create(RegistryKey<Registry<T>> registryKey, String modId) {
        throw new AssertionError();
    }
    
    public abstract <V extends T> RegistrySupplier<V> add(String name, V object);
    
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
