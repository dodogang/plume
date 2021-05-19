package net.dodogang.plume.ash.client.registry.fabric;

import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class SpriteRegistryImpl {
    /**
     * Registers sprites to be loaded to a specified texture atlas.
     *
     * @param modId the mod id for forge's mod event bus
     * @param atlas the atlas texture. For vanilla's atlases see {@link TexturedRenderLayers}
     * @param spriteLocations the sprite locations to be registered.
     */
    public static void register(String modId, Identifier atlas, Identifier... spriteLocations) {
        ClientSpriteRegistryCallback.event(atlas).register((atlasTexture, registry) -> {
            for (Identifier sprite : spriteLocations) {
                registry.register(sprite);
            }
        });
    }
}
