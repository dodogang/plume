package net.dodogang.plume.screen;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class PlumeCraftingScreenHandler extends CraftingScreenHandler {
    private final ScreenHandlerContext context;
    private final Block block;

    public PlumeCraftingScreenHandler(int syncId, PlayerInventory inventory, ScreenHandlerContext context, Block block) {
        super(syncId, inventory, context);
        this.context = context;
        this.block = block;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, this.block);
    }
}
