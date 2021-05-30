package net.dodogang.plume.mixin.donor.client.cosmetic;

import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @SuppressWarnings("unused")
    private PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (!this.isSpectator() && !this.isInvisible()) {
            DonorData data = DonorDataManager.get(Util.parseStringUUID(this.getUuid()));
            Cosmetic cosmetic = data.getSelectedCosmetics().get(CosmeticSlot.TICKER);
            if (cosmetic != null) {
                CosmeticsClient.getTickers().get(cosmetic).tick(this.world, this);
            }
        }
    }

    @Inject(method = "isPartVisible", at = @At("HEAD"), cancellable = true)
    private void checkForCosmeticCapeVisible(PlayerModelPart part, CallbackInfoReturnable<Boolean> cir) {
        if (part == PlayerModelPart.CAPE) {
            DonorData data = DonorDataManager.get(Util.parseStringUUID(this.getUuid()));
            Cosmetic cosmetic = data.getSelectedCosmetics().get(CosmeticSlot.BACK);
            if (cosmetic != null && !CosmeticsClient.getCapeModels().containsKey(cosmetic) && !data.getConfig(DonorData.ConfigOptions.BOOL_RENDER_CAPES_AND_ELYTRAS).getAsBoolean()) {
                cir.setReturnValue(false);
            }
        }
    }
}
