package net.dodogang.plume.ash.client.registry.forge;

import net.dodogang.plume.ash.client.registry.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.dodogang.plume.ash.mixin.client.forge.ItemAccessor;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
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
        ((ItemAccessor)item.asItem()).setIster(() -> new CustomBuiltinModelItemRenderer(renderer));
    }

    private static final class CustomBuiltinModelItemRenderer extends BuiltinModelItemRenderer {
        private final DynamicItemRenderer renderer;

        public CustomBuiltinModelItemRenderer(DynamicItemRenderer renderer) {
            this.renderer = renderer;
        }

        @Override
        public void render(
                ItemStack stack,
                ModelTransformation.Mode mode,
                MatrixStack matrices,
                VertexConsumerProvider vertexConsumers,
                int light,
                int overlay
        ) {
            renderer.render(stack, mode, matrices, vertexConsumers, light, overlay);
        }
    }
}
