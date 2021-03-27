package net.dodogang.plume.ash.client.registry.fabric;

import net.dodogang.plume.ash.client.registry.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.item.ItemConvertible;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class BuiltinItemRendererRegistryImpl {
    /**
     * Registers the renderer for the item.
     *
     * <p>Note that the item's JSON model must also extend {@code minecraft:builtin/entity}.
     *
     * @param item the item
     * @param renderer the renderer
     */
    public static void register(ItemConvertible item, DynamicItemRenderer renderer) {
        BuiltinItemRendererRegistry.INSTANCE.register(item, renderer::render);
    }
}
