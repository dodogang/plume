package net.dodogang.plume.ash.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class RegistrySupplier<T> implements Supplier<T> {
    private final Identifier id;
    private final Registry<T> registry;
    private final T initialValue;

    private T cachedValue;

    public RegistrySupplier(Identifier id, Registry<T> registry, T initialValue) {
        this.id = id;
        this.registry = registry;
        this.initialValue = initialValue;
        this.cachedValue = null;
    }

    /**
     * Gets the value from the vanilla or forge registry of the id. This allows
     * for registry replacement on forge to work.
     *
     * @return the registered value or null. Use {@link RegistrySupplier#isPresent()}
     * to see if it has been registered.
     */
    @Override
    public T get() {
        if (cachedValue != null) {
            return cachedValue;
        }
        T value = registry.get(id);

        // Get id of returned value to make sure it's not the default value.
        // If it is the default value, this hasn't been registered so return null.
        Identifier valueId = registry.getId(value);
        return id.equals(valueId) ? cachedValue = value : null;
    }

    /**
     * Gets the initially registered value. Only use if the value is required
     * before registration has occurred.
     *
     * @return the initially registered value
     */
    public T getInitialValue() {
        return isPresent() ? get() : initialValue;
    }

    /**
     * @return true if an object has been registered with id
     */
    public boolean isPresent() {
        return get() != null;
    }
}
