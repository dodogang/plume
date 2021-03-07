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
     * Only use if necessary. eg. during the registration phase.
     * @return the same value that was passed to registration.
     */
    public T getInitialValue() {
        return isPresent() ? get() : initialValue;
    }

    public boolean isPresent() {
        return get() != null;
    }
}
