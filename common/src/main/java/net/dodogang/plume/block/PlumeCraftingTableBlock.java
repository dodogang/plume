package net.dodogang.plume.block;

import net.dodogang.plume.screen.PlumeCraftingScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlumeCraftingTableBlock extends CraftingTableBlock {
    private static final Text TITLE = new TranslatableText("container.crafting");

    public PlumeCraftingTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory(
                (syncId, inventory, player) -> new PlumeCraftingScreenHandler(
                    syncId,
                    inventory,
                    ScreenHandlerContext.create(world, pos),
                    state.getBlock()
                ), TITLE);
    }
}
