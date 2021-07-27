package net.dodogang.plume.ash.client.registry;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.KeyBinding;

@Environment(EnvType.CLIENT)
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
