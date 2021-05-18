package net.dodogang.plume.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

/**
 * An extension of {@link PlantBlock} that can hang from a ceiling.
 */
@SuppressWarnings("unused")
public class CeilingPlantBlock extends PlantBlock {
    public CeilingPlantBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected boolean canPlantBelow(BlockState ceiling, WorldView world, BlockPos pos) {
        return this.canPlantOnTop(ceiling, world, pos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return this.canPlantBelow(world.getBlockState(pos.up()), world, pos);
    }
}
