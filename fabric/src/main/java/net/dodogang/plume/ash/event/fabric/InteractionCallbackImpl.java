package net.dodogang.plume.ash.event.fabric;

import net.dodogang.plume.ash.event.InteractionCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class InteractionCallbackImpl {
    public static void registerRightClickBlock(InteractionCallback.RightClickBlock callback) {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!player.isSpectator()) {
                return callback.click(player, world, hand, hitResult.getBlockPos(), hitResult.getSide());
            } else {
                return ActionResult.PASS;
            }
        });
    }
}
