package net.dodogang.plume.ash.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

@Environment(EnvType.CLIENT)
public final class BlockEntityRendererRegistry {
    /**
     * Registers a renderer to a block entity type.
     *
     * @param type type of block entity to register the renderer to
     * @param factory a function that contructs a BlockEntityRenderer e.g. ChestRenderer::new
     * @param <T> extends BlockEntity
     */
    @ExpectPlatform
    public static <T extends BlockEntity> void register(BlockEntityType<T> type, BlockEntityRendererFactory<? super T> factory) {
        throw new AssertionError();
    }
}
