package net.dodogang.plume.mixin.donor.client.cosmetic;

import com.mojang.authlib.GameProfile;
import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {
    @SuppressWarnings("unused")
    private AbstractClientPlayerEntityMixin(World world, BlockPos blockPos, float f, GameProfile gameProfile) {
        super(world, blockPos, f, gameProfile);
    }

    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    private void imitateCapeTexture(CallbackInfoReturnable<Identifier> cir) {
        DonorData data = DonorDataManager.get(Util.parseStringUUID(this.getUuid()));
        Cosmetic cosmetic = data.getSelectedCosmetics().get(CosmeticSlot.BACK);
        if (cosmetic != null && data.getConfig(DonorData.ConfigOptions.BOOL_RENDER_CAPES_AND_ELYTRAS).getAsBoolean() && CosmeticsClient.getCapeModels().containsKey(cosmetic)) {
            cir.setReturnValue(Util.EMPTY_TEXTURE);
        }
    }
}
