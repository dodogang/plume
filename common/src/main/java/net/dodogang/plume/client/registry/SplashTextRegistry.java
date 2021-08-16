package net.dodogang.plume.client.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.dodogang.plume.Plume;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.HashSet;

@Environment(EnvType.CLIENT)
public final class SplashTextRegistry {
    private SplashTextRegistry() {}

    private static final HashSet<String> SPLASH_TEXTS = new HashSet<>();
    private static final HashMap<String, Integer> SPLASH_COLORS = new HashMap<>();

    private static ImmutableList<String> splashTexts_cache = null;
    private static ImmutableMap<String, Integer> splashColors_cache = null;

    /**
     * Registers a splash text to be added to the title screen.
     *
     * @param text The splash text to be added
     */
    public static void register(String text) {
        if (SPLASH_TEXTS.contains(text)) {
            Plume.log(Level.WARN, String.format("Overriding duplicate splash '%s'", text));
        }

        SPLASH_TEXTS.add(text);
    }

    /**
     * Registers a splash text to be added to the title screen.
     *
     * @param text The splash text to be added
     * @param color A color for the registered splash text to be displayed in
     */
    public static void register(String text, int color) {
        register(text);
        SPLASH_COLORS.put(text, color);
    }

    // ---

    public static ImmutableList<String> getSplashTexts() {
        if (splashTexts_cache == null) {
            splashTexts_cache = ImmutableList.copyOf(SPLASH_TEXTS);
        }

        return splashTexts_cache;
    }
    public static ImmutableMap<String, Integer> getSplashTextColorMap() {
        if (splashColors_cache == null) {
            splashColors_cache = ImmutableMap.copyOf(SPLASH_COLORS);
        }

        return splashColors_cache;
    }
}
