package net.dodogang.plume.ash.client.registry.forge;

import net.dodogang.plume.ash.client.registry.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.item.ItemConvertible;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.ApiStatus;

@OnlyIn(Dist.CLIENT)
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
        // TODO ((ItemAccessor)item.asItem()).setIster(() -> new CustomBuiltinModelItemRenderer(renderer));
    }

    /*private static final class CustomBuiltinModelItemRenderer extends BuiltinModelItemRenderer {
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
    }*/ // TODO
}
