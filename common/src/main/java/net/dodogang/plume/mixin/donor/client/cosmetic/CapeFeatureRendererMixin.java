package net.dodogang.plume.mixin.donor.client.cosmetic;

import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.dodogang.plume.donor.client.cosmetic.model.CloakCosmeticModel;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(CapeFeatureRenderer.class)
public abstract class CapeFeatureRendererMixin extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    @SuppressWarnings("unused")
    private CapeFeatureRendererMixin(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> ctx) {
        super(ctx);
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void cancelRender(MatrixStack matrices, VertexConsumerProvider vertices, int i, AbstractClientPlayerEntity entity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        CosmeticsClient.cancelCapeOrElytraRender(entity, ci);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/PlayerEntityModel;renderCape(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;II)V"), cancellable = true)
    private void replaceCape(MatrixStack matrices, VertexConsumerProvider vertices, int i, AbstractClientPlayerEntity entity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        DonorData data = DonorDataManager.get(Util.parseStringUUID(entity.getUuid()));
        Cosmetic cosmetic = data.getSelectedCosmetics().get(CosmeticSlot.BACK);
        if (cosmetic != null && CosmeticsClient.getCloakModels().containsKey(cosmetic) && data.getConfig(DonorData.ConfigOptions.BOOL_RENDER_CLOAKS_AND_ELYTRAS).getAsBoolean()) {
            // TODO this is creating a new model every frame??
            CloakCosmeticModel cloak = CosmeticsClient.getCloakModels().get(cosmetic).apply(CapeFeatureRenderer.class.cast(this));
            cloak.setAngles(entity, f, g, h, i, j);
            cloak.render(matrices, vertices.getBuffer(RenderLayer.getEntitySolid(cloak.getTexture())), i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrices.pop();

            ci.cancel();
        }
    }
}
