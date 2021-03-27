package net.dodogang.plume.registry;

import com.google.common.collect.ImmutableSet;
import net.dodogang.plume.mixin.PointOfInterestTypeAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Util;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.*;

public final class PointOfInterestTypeAppender {
    private PointOfInterestTypeAppender() {}

    /**
     * Appends the given block states onto the list of block states for a
     * given point of interest. This allows for villagers to change their
     * profession when near the given states.
     *
     * @param poiType the PointOfInterestType to append states to
     * @param states the states to append
     */
    public static void appendBlockStates(PointOfInterestType poiType, BlockState... states) {
        // Put block states in overall BlockState -> PointOfInterestType map.
        Map<BlockState, PointOfInterestType> blockStatePoiMap = PointOfInterestTypeAccessor.getBlockStatePoiMap();
        for (BlockState state : states) {
            PointOfInterestType previousPoiType = blockStatePoiMap.put(state, poiType);

            // previousPoiType is not null if our state was already in the map.
            // A state can only be in one PointOfInterestType, so we throw an error.
            if (previousPoiType != null) {
                throw Util.throwOrPause(new IllegalStateException(
                        String.format("%s is defined for too many PointOfInterestTypes.", state)
                ));
            }
        }

        // Get mutable access to poiType's blockState set.
        PointOfInterestTypeAccessor poiTypeAccessor = (PointOfInterestTypeAccessor) poiType;

        // Copy the set and add our states to the list.
        List<BlockState> blockStates = new ArrayList<>(poiTypeAccessor.getBlockStates());
        blockStates.addAll(Arrays.asList(states));

        // Replace the original set with one that has our values.
        poiTypeAccessor.setBlockStates(ImmutableSet.copyOf(blockStates));
    }

    /**
     * Appends the given block's states onto the list of block states for a
     * given point of interest. This allows for villagers to change their
     * profession when near the given states.
     *
     * @param poiType the PointOfInterestType to append states to
     * @param blocks the blocks whose states to append
     */
    public static void appendBlocks(PointOfInterestType poiType, Block... blocks) {
        List<BlockState> states = new ArrayList<>();
        for (Block block : blocks) {
            states.addAll(ImmutableSet.copyOf(block.getStateManager().getStates()));
        }

        BlockState[] array = new BlockState[states.size()];
        appendBlockStates(poiType, states.toArray(array));
    }
}
