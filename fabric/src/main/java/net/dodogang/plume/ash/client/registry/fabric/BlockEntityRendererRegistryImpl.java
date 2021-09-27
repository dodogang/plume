package net.dodogang.plume.ash.client.registry.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class BlockEntityRendererRegistryImpl {
    /**
     * Registers a renderer to a block entity type.
     *
     * @param beType type of block entity to register the renderer to
     * @param renderer a function that contructs a BlockEntityRenderer e.g. ChestRenderer::new
     * @param <E> extends BlockEntity
     */
    public static <E extends BlockEntity> void register(
            BlockEntityType<E> beType,
            BlockEntityRendererFactory<? super E> renderer
    ) {
        BlockEntityRendererRegistry.register(beType, renderer);
    }
}
