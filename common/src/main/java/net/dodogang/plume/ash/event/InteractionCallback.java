package net.dodogang.plume.ash.event;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public final class InteractionCallback {
    private InteractionCallback() {}

    /**
     * Callback for right-clicking a block.
     * Does not fire if the player's game mode is spectator mode.
     *
     * <p>Upon return:
     * <ul><li>SUCCESS cancels further processing and, on the client, sends a packet to the server.
     * <li>PASS falls back to further processing.
     * <li>FAIL cancels further processing and does not send a packet to the server.</ul>
     */
    public interface RightClickBlock {
        ActionResult click(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction side);

        static void register(RightClickBlock callback) {
            registerRightClickBlock(callback);
        }
    }

    @ExpectPlatform
    private static void registerRightClickBlock(RightClickBlock callback) {
        throw new AssertionError();
    }
}
