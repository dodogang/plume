package net.dodogang.plume.mixin.cosmetic.client;

import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
            DonorData cosmetics = DonorDataManager.get(Util.parseStringUUID(this.getUuid()));
            if (cosmetics != null) {
                Cosmetic cosmetic = cosmetics.getSelectedCosmetics().get(CosmeticSlot.TICKER);
                if (cosmetic != null) {
                    CosmeticsClient.getTickers().get(cosmetic).tick(this.world, this);
                }
            }
        }
    }
}
