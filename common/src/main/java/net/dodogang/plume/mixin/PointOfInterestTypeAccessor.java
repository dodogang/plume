package net.dodogang.plume.mixin;

import net.dodogang.plume.registry.PointOfInterestTypeAppender;
import net.minecraft.block.BlockState;
import net.minecraft.world.poi.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

/**
 * Necessary because these fields are private and are needed to add points of
 * interest to villagers. See {@link PointOfInterestTypeAppender}
 */
@Mixin(PointOfInterestType.class)
public interface PointOfInterestTypeAccessor {
    @Accessor("BLOCK_STATE_TO_POINT_OF_INTEREST_TYPE")
    static Map<BlockState, PointOfInterestType> getBlockStatePoiMap() {
        throw new AssertionError(); // This will be replaced by mixin with the proper return.
    }

    @Accessor("blockStates")
    Set<BlockState> getBlockStates();

    @Mutable
    @Accessor("blockStates")
    void setBlockStates(Set<BlockState> blockStates);
}
