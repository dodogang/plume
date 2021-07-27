package net.dodogang.plume.ash.client.registry.forge;

import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.ApiStatus;

@OnlyIn(Dist.CLIENT)
@ApiStatus.Internal
public final class RenderLayerRegistryImpl {
    /**
     * Map (or re-map) multiple blocks with a render layer.  Re-mapping is not
     * recommended but if done, last one in wins. Must be called from client
     * thread prior to world load/rendering. Best practice will be to call from
     * mod's client initializer.
     *
     * @param renderLayer Render layer.  Should be one of the layers used for terrain rendering.
     * @param blocks Identifies blocks to be mapped.
     */
    public static void setRenderLayer(RenderLayer renderLayer, Block... blocks) {
        for (Block block : blocks) {
            RenderLayers.setRenderLayer(block, renderLayer);
        }
    }

    /**
     * Map (or re-map) multiple fluids with a render layer.  Re-mapping is not
     * recommended but if done, last one in wins. Must be called from client
     * thread prior to world load/rendering. Best practice will be to call from
     * mod's client initializer.
     *
     * @param renderLayer Render layer.  Should be one of the layers used for terrain rendering.
     * @param fluids Identifies fluids to be mapped.
     */
    public static void setRenderLayer(RenderLayer renderLayer, Fluid... fluids) {
        for (Fluid fluid : fluids) {
            RenderLayers.setRenderLayer(fluid, renderLayer);
        }
    }
}
