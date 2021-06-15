package net.dodogang.plume.mixin;

import net.dodogang.plume.ash.tag.AshItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractDonkeyEntity.class)
public abstract class AbstractDonkeyEntityMixin extends HorseBaseEntity {
    @Shadow public abstract boolean hasChest();

    @Shadow public abstract void setHasChest(boolean bl);

    @Shadow protected abstract void playAddChestSound();

    public AbstractDonkeyEntityMixin(EntityType<? extends HorseBaseEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Replaces hardcoded `itemStack.getItem() == Blocks.CHEST.asItem()` with a wooden_chests tag.
     */
    @Inject(at = @At("HEAD"), method = "equip", cancellable = true)
    private void plume_equipWoodenChest(int i, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (i == 499) {
            if (!this.hasChest() && AshItemTags.WOODEN_CHESTS.contains(stack.getItem())) {
                this.setHasChest(true);
                this.onChestedStatusChanged();
                cir.setReturnValue(true);
            }
        }
    }

    /**
     * Replaces hardcoded `itemStack.getItem() == Blocks.CHEST.asItem()` with a wooden_chests tag.
     */
    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    private void plume_interactWithWoodenChest(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (!stack.isEmpty()) {
            if (!this.hasChest() && AshItemTags.WOODEN_CHESTS.contains(stack.getItem())) {
                this.setHasChest(true);
                this.playAddChestSound();
                if (!player.abilities.creativeMode) {
                    stack.decrement(1);
                }
                this.onChestedStatusChanged();
                cir.setReturnValue(ActionResult.success(this.world.isClient));
            }
        }
    }
}
