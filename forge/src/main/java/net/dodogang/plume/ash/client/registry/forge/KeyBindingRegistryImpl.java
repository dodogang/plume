package net.dodogang.plume.ash.client.registry.forge;

import net.minecraft.client.option.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;
import org.jetbrains.annotations.ApiStatus;

@OnlyIn(Dist.CLIENT)
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
