package net.dodogang.plume.mixin;

import net.dodogang.plume.Plume;
import net.dodogang.plume.ash.tag.AshItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractDonkeyEntity.class)
public abstract class AbstractDonkeyEntityMixin extends HorseBaseEntity {
    private static final TrackedData<String> PLUME_CHEST_TYPE = DataTracker.registerData(AbstractDonkeyEntity.class, TrackedDataHandlerRegistry.STRING);

    @Shadow public abstract boolean hasChest();
    @Shadow public abstract void setHasChest(boolean bl);
    @Shadow protected abstract void playAddChestSound();

    private AbstractDonkeyEntityMixin(EntityType<? extends HorseBaseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void injectCustomData(CallbackInfo ci) {
        this.dataTracker.startTracking(PLUME_CHEST_TYPE, null);
    }
    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    private void injectCustomNbtRead(CompoundTag tag, CallbackInfo ci) {
        if (this.hasChest()) {
            CompoundTag plumeTag = tag.getCompound(Plume.MOD_ID);
            if (plumeTag != null) {
                this.dataTracker.set(PLUME_CHEST_TYPE, plumeTag.getString("ChestType"));
            }
        }
    }
    @Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
    private void injectCustomNbtWrite(CompoundTag tag, CallbackInfo ci) {
        if (this.hasChest()) {
            CompoundTag plumeTag = new CompoundTag();
            plumeTag.putString("ChestType", this.dataTracker.get(PLUME_CHEST_TYPE));

            tag.put(Plume.MOD_ID, plumeTag);
        }
    }

    /**
     * Replaces hardcoded <code>Blocks.CHEST</code> with the stored <code>plume.ChestType</code> tag.
     */
    @ModifyArg(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AbstractDonkeyEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;"), index = 0)
    private ItemConvertible fixChestDrop(ItemConvertible original) {
        Item chestItem = Registry.ITEM.get(Identifier.tryParse(this.dataTracker.get(PLUME_CHEST_TYPE)));
        if (chestItem != Blocks.CHEST.asItem() && AshItemTags.WOODEN_CHESTS.contains(chestItem)) {
            return chestItem;
        }

        return original;
    }

    /**
     * Replaces hardcoded <code>itemStack.getItem() == Blocks.CHEST.asItem()</code> with a <code>wooden_chests</code> tag.
     */
    @Inject(at = @At("HEAD"), method = "equip", cancellable = true)
    private void plume_equipWoodenChest(int i, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (i == 499) {
            if (!this.hasChest() && AshItemTags.WOODEN_CHESTS.contains(stack.getItem())) {
                this.dataTracker.set(PLUME_CHEST_TYPE, Registry.ITEM.getId(stack.getItem()).toString());
                this.setHasChest(true);
                this.onChestedStatusChanged();
                cir.setReturnValue(true);
            }
        }
    }

    /**
     * Replaces hardcoded <code>itemStack.getItem() == Blocks.CHEST.asItem()</code> with a <code>wooden_chests</code> tag.
     */
    @Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
    private void plume_interactWithWoodenChest(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (!stack.isEmpty()) {
            if (!this.hasChest() && AshItemTags.WOODEN_CHESTS.contains(stack.getItem())) {
                this.dataTracker.set(PLUME_CHEST_TYPE, Registry.ITEM.getId(stack.getItem()).toString());
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
