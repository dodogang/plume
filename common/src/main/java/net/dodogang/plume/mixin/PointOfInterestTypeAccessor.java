package net.dodogang.plume.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.world.poi.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

@Mixin(PointOfInterestType.class)
public interface PointOfInterestTypeAccessor {
    @Accessor("BLOCK_STATE_TO_POINT_OF_INTEREST_TYPE")
    static Map<BlockState, PointOfInterestType> getBlockStatePoiMap() {
        throw new AssertionError(); // This will be replaced by mixin with the proper return.
    }

    @Accessor("blockStates")
    Set<BlockState> getBlockStates();

    @Accessor("blockStates")
    void setBlockStates(Set<BlockState> blockStates);
}
