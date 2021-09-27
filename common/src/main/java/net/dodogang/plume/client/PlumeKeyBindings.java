package net.dodogang.plume.client;

import net.dodogang.plume.Plume;
import net.dodogang.plume.ash.client.registry.KeyBindingRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

@Environment(EnvType.CLIENT)
public class PlumeKeyBindings {
    private static KeyBinding register(String id, int code) {
        return KeyBindingRegistry.register(new KeyBinding(
                "key." + Plume.MOD_ID + "." + id,
                InputUtil.Type.KEYSYM, code,
                "key.categories." + Plume.MOD_ID
            )
        );
    }
}
