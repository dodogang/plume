package net.dodogang.plume.ash.client.registry;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public final class SpriteRegistry {
    private SpriteRegistry() {}

    /**
     * Registers sprites to be loaded to a specified texture atlas.
     *
     * @param modId the mod id for forge's mod event bus
     * @param atlas the atlas texture. For vanilla's atlases see {@link TexturedRenderLayers}
     * @param spriteLocations the sprite locations to be registered.
     */
    @ExpectPlatform
    public static void register(String modId, Identifier atlas, Identifier... spriteLocations) {
        throw new AssertionError();
    }
}
