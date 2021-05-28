package net.dodogang.plume.mixin.cosmetic.client;

import net.dodogang.plume.cosmetic.Cosmetic;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.CosmeticSlot;
import net.dodogang.plume.cosmetic.CosmeticsManager;
import net.dodogang.plume.cosmetic.client.CosmeticsManagerClient;
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
            CosmeticPlayerData cosmetics = CosmeticsManager.getLocalData(Util.parseStringUUID(this.getUuid()));
            if (cosmetics != null) {
                Cosmetic cosmetic = cosmetics.getCosmetics().get(CosmeticSlot.TICKER);
                if (cosmetic != null) {
                    CosmeticsManagerClient.getTickers().get(cosmetic).tick(this.world, this);
                }
            }
        }
    }
}
