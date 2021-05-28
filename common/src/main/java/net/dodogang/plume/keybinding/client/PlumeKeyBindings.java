package net.dodogang.plume.keybinding.client;

import net.dodogang.plume.Plume;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class PlumeKeyBindings {
    public static final KeyBinding OPEN_COSMETICS_MENU = register("open_cosmetics_menu", GLFW.GLFW_KEY_UNKNOWN);

    private static KeyBinding register(String id, int code) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + Plume.MOD_ID + "." + id,
                InputUtil.Type.KEYSYM, code,
                "key.categories." + Plume.MOD_ID
            )
        );
    }
}
