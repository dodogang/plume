package net.dodogang.plume.mixin.client;

import com.mojang.authlib.GameProfile;
import net.dodogang.plume.client.cosmetic.ClientCosmeticManager;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.TickingCosmetic;
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

    @SuppressWarnings("unused")
    private ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        if (!this.isSpectator() && !this.isInvisibleTo(this.client.player)) {
            CosmeticPlayerData cosmetics = ClientCosmeticManager.LOCAL_DATA.get(this.uuid);
            if (cosmetics != null) {
                cosmetics.getCosmetics().forEach((slot, cosmetic) -> {
                    if (cosmetic instanceof TickingCosmetic && ((TickingCosmetic) cosmetic).shouldTick(this)) {
                        ((TickingCosmetic) cosmetic).tick(this.world, this);
                    }
                });
            }
        }
    }
}
