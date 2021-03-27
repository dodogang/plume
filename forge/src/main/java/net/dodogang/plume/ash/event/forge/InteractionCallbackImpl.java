package net.dodogang.plume.ash.event.forge;

import net.dodogang.plume.ash.event.InteractionCallback;
import net.minecraft.util.ActionResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class InteractionCallbackImpl {
    public static void registerRightClickBlock(InteractionCallback.RightClickBlock callback) {
        MinecraftForge.EVENT_BUS.<PlayerInteractEvent.RightClickBlock>addListener(e -> {
            if (!e.getPlayer().isSpectator()) {
                ActionResult result = callback.click(e.getPlayer(), e.getWorld(), e.getHand(), e.getPos(), e.getFace());

                e.setCancellationResult(result);
            }
        });
    }
}
