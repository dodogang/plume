package net.dodogang.plume.ash.client.registry.forge;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.ApiStatus;

@OnlyIn(Dist.CLIENT)
@ApiStatus.Internal
public final class BlockEntityRendererRegistryImpl {
    /**
     * Registers a renderer to a block entity type.
     *
     * @param type type of block entity to register the renderer to
     * @param factory a function that contructs a BlockEntityRenderer e.g. ChestRenderer::new
     * @param <T> extends BlockEntity
     */
    public static <T extends BlockEntity> void register(BlockEntityType<T> type, BlockEntityRendererFactory<? super T> factory) {
        BlockEntityRendererFactories.register(type, factory);
    }
}
