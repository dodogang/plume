package net.dodogang.plume.ash.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public final class BuiltinItemRendererRegistry {
    private BuiltinItemRendererRegistry() {}

    /**
     * Registers a renderer for an item.
     *
     * <p>Note that the item's JSON model must also extend {@code minecraft:builtin/entity}.
     *
     * @param item a item
     * @param renderer a renderer
     */
    @ExpectPlatform
    public static void register(ItemConvertible item, DynamicItemRenderer renderer) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface DynamicItemRenderer {
        /**
         * Renders an item stack.
         *
         * @param stack           the rendered item stack
         * @param mode            the model transformation mode
         * @param matrices        the matrix stack
         * @param vertexConsumers the vertex consumer provider
         * @param light           packed lightmap coordinates
         * @param overlay         the overlay UV passed to {@link net.minecraft.client.render.VertexConsumer#overlay(int)}
         */
        void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay);
    }

    /**
     * Helper method to register a block entity's renderer for an item.
     *
     * <p>Note that the item's JSON model must also extend {@code minecraft:builtin/entity}.
     *
     * @param item a item
     * @param blockEntity a block entity
     */
    public static void registerBlockEntityRenderer(ItemConvertible item, BlockEntity blockEntity) {
        register(item, (stack, mode, matrices, vertexConsumers, light, overlay) -> {
            matrices.push();
            MinecraftClient client = MinecraftClient.getInstance();
            BlockEntityRenderDispatcher dispatcher = client.getBlockEntityRenderDispatcher();
            dispatcher.renderEntity(blockEntity, matrices, vertexConsumers, light, overlay);
            matrices.pop();
        });
    }
}
