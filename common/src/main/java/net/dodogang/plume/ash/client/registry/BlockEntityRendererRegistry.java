package net.dodogang.plume.ash.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;

import java.util.function.Function;

public final class BlockEntityRendererRegistry {
    /**
     * Registers a renderer to a block entity type.
     *
     * @param beType type of block entity to register the renderer to
     * @param renderer a function that contructs a BlockEntityRenderer e.g. ChestRenderer::new
     * @param <E> extends BlockEntity
     */
    @ExpectPlatform
    public static <E extends BlockEntity> void register(
            BlockEntityType<E> beType,
            Function<BlockEntityRenderDispatcher, BlockEntityRenderer<E>> renderer
    ) {
        throw new AssertionError();
    }
}
