package net.dodogang.plume.ash.client.registry.fabric;

import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Function;

@ApiStatus.Internal
public final class BlockEntityRendererRegistryImpl {
    /**
     * Registers a renderer to a block entity type.
     *
     * @param beType type of block entity to register the renderer to
     * @param renderer a function that contructs a BlockEntityRenderer e.g. ChestRenderer::new
     * @param <T> extends BlockEntity
     */
    public static <T extends BlockEntity> void register(
            BlockEntityType<T> beType,
            Function<BlockEntityRenderDispatcher, BlockEntityRenderer<? super T>> renderer
    ) {
        BlockEntityRendererRegistry.INSTANCE.register(beType, renderer);
    }
}
