package net.dodogang.plume.ash.client.registry.forge;

import net.minecraft.client.options.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class KeyBindingRegistryImpl {
    /**
     * Registers the keybinding and add the keybinding category if required.
     *
     * @param keyBinding the keybinding
     * @return the keybinding itself
     */
    public static KeyBinding register(KeyBinding keyBinding) {
        ClientRegistry.registerKeyBinding(keyBinding);
        return keyBinding;
    }
}
