package net.dodogang.plume.ash.client.registry;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.client.options.KeyBinding;

public final class KeyBindingRegistry {
    private KeyBindingRegistry() {}

    /**
     * Registers the keybinding and add the keybinding category if required.
     *
     * @param keyBinding the keybinding
     * @return the keybinding itself
     */
    @ExpectPlatform
    public static KeyBinding register(KeyBinding keyBinding) {
        throw new AssertionError();
    }
}
