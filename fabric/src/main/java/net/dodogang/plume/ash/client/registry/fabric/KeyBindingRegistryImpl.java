package net.dodogang.plume.ash.client.registry.fabric;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
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
        return KeyBindingHelper.registerKeyBinding(keyBinding);
    }
}
