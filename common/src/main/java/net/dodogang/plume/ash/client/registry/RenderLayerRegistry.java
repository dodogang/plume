package net.dodogang.plume.ash.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.Fluid;

@Environment(EnvType.CLIENT)
public final class RenderLayerRegistry {
    /**
     * Map (or re-map) multiple blocks with a render layer.  Re-mapping is not
     * recommended but if done, last one in wins. Must be called from client
     * thread prior to world load/rendering. Best practice will be to call from
     * mod's client initializer.
     *
     * @param renderLayer Render layer.  Should be one of the layers used for terrain rendering.
     * @param blocks Identifies blocks to be mapped.
     */
    @ExpectPlatform
    public static void setRenderLayer(RenderLayer renderLayer, Block... blocks) {
        throw new AssertionError();
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
    @ExpectPlatform
    public static void setRenderLayer(RenderLayer renderLayer, Fluid... fluids) {
        throw new AssertionError();
    }
}
