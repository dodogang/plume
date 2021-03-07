package net.dodogang.plume.ash.registry.forge;

import net.dodogang.plume.ash.forge.ModEventBus;
import net.dodogang.plume.ash.registry.BatchedRegister;
import net.dodogang.plume.ash.registry.RegistrySupplier;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;

public class BatchedRegisterImpl<T extends IForgeRegistryEntry<T>> extends BatchedRegister<T> {
    private final DeferredRegister<T> deferredRegister;

    public BatchedRegisterImpl(RegistryKey<Registry<T>> registryKey, String modId) {
        super(registryKey, modId);
        this.deferredRegister = DeferredRegister.create(RegistryManager.ACTIVE.getRegistry(registryKey), modId);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> BatchedRegister<T> create(RegistryKey<Registry<T>> registryKey, String modId) {
        return new BatchedRegisterImpl(registryKey, modId);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <V extends T> RegistrySupplier<V> add(String name, V object) {
        deferredRegister.register(name, () -> object);
        return new RegistrySupplier(new Identifier(modId, name), findRegistryByKey(registryKey), object);
    }

    @Override
    protected void registerImpl() {
        IEventBus modEventBus = ModEventBus.getModEventBus(modId).orElseThrow(() -> new IllegalStateException(
                "Attempted to register BatchedRegister before registering a ModEventBus for modid '" + modId + "'.")
        );
        deferredRegister.register(modEventBus);
    }
}
