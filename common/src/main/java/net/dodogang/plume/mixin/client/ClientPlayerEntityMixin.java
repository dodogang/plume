package net.dodogang.plume.mixin.client;

import com.mojang.authlib.GameProfile;
import net.dodogang.plume.client.cosmetic.ClientCosmeticsManager;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.ParticleCosmetic;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    @Shadow @Final protected MinecraftClient client;

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        CosmeticPlayerData cosmeticData = ClientCosmeticsManager.PLAYER_DATA.get(this.uuid);
        if (cosmeticData != null) {
            cosmeticData.getCosmetics().forEach(
                (cosmeticSlot, cosmetic) -> {
                    if (cosmetic instanceof ParticleCosmetic) {
                        ((ParticleCosmetic) cosmetic).spawnParticle(this.world, this);
                    }
                }
            );
        }
    }
}
