package net.dodogang.plume.mixin.cosmetic;

import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.Cosmetics;
import net.dodogang.plume.cosmetic.CosmeticsManager;
import net.dodogang.plume.cosmetic.TickingCosmetic;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @SuppressWarnings("ConstantConditions")
    @Environment(EnvType.CLIENT)
    @Inject(method = "handleStatus", at = @At("HEAD"), cancellable = true)
    private void handleCosmeticStatus(byte status, CallbackInfo ci) {
        if (status == Cosmetics.AURA_STATUS) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (!client.options.getPerspective().isFirstPerson() || client.player != (PlayerEntity.class.cast(this))) {
                this.world.addParticle(new DustParticleEffect(1.0f, 0.0f, 1.0f, 1.0f), this.getParticleX(0.7d), this.getRandomBodyY(), this.getParticleZ(0.7d), 0.075d, 0.075d, 0.075d);
            }
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        if (!this.world.isClient && !this.isSpectator() && !this.isInvisible()) {
            CosmeticPlayerData cosmetics = CosmeticsManager.LOCAL_DATA.get(Util.parseStringUUID(this.uuid));
            if (cosmetics != null) {
                PlayerEntity $this = PlayerEntity.class.cast(this);
                cosmetics.getCosmetics().forEach((slot, cosmetic) -> {
                    if (cosmetic instanceof TickingCosmetic) {
                        ((TickingCosmetic) cosmetic).tick(this.world, $this);
                    }
                });
            }
        }
    }
}
