package net.dodogang.plume.mixin.cosmetic.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.util.PlayerUUID;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Environment(EnvType.CLIENT)
@Mixin(PlayerListHud.class)
public class PlayerListHudMixin extends DrawableHelper {
    @Shadow @Final private MinecraftClient client;

    @SuppressWarnings("deprecation")
    @Inject(method = "renderLatencyIcon", at = @At("TAIL"))
    private void addExtraIcons(MatrixStack matrices, int i, int j, int y, PlayerListEntry entry, CallbackInfo ci) {
        UUID uuid = entry.getProfile().getId();
        DonorData data = DonorDataManager.get(uuid);
        boolean isTeamMember = PlayerUUID.$TEAM_MEMBERS.contains(uuid.toString());

        if ((data != null && (data.isPatron() || data.isNitro())) || isTeamMember) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.client.getTextureManager().bindTexture(isTeamMember ? Util.DONOR_BADGE_TEAM : Util.DONOR_BADGE);

            this.setZOffset(this.getZOffset() + 100);
            DrawableHelper.drawTexture(matrices, j + i - 11, y, 0, 0, 10, 8, 10, 8);
            this.setZOffset(this.getZOffset() - 100);
        }
    }
}
