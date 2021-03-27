package net.dodogang.plume.ash.client.registry.forge;

import net.dodogang.plume.ash.forge.ModEventBus;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.util.Identifier;
import net.minecraftforge.client.event.TextureStitchEvent;
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
        ModEventBus.getModEventBusOrThrow(modId).<TextureStitchEvent.Pre>addListener(e -> {
            if (e.getMap().getId().equals(atlas)) {
                for (Identifier spriteLocation : spriteLocations) {
                    e.addSprite(spriteLocation);
                }
            }
        });
    }
}
