package net.dodogang.plume.mixin.donor.client.cosmetic;

import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.client.cosmetic.CosmeticsClient;
import net.dodogang.plume.donor.client.cosmetic.model.ElytraCosmeticModel;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ElytraFeatureRenderer.class)
public abstract class ElytraFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    @SuppressWarnings("unused")
    private ElytraFeatureRendererMixin(FeatureRendererContext<T, M> ctx) {
        super(ctx);
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void cancelRender(MatrixStack matrices, VertexConsumerProvider vertices, int i, T entity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        CosmeticsClient.cancelCapeOrElytraRender(entity, ci);
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/ElytraFeatureRenderer;getContextModel()Lnet/minecraft/client/render/entity/model/EntityModel;"), cancellable = true)
    private void replaceCape(MatrixStack matrices, VertexConsumerProvider vertices, int i, T entity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        DonorData data = DonorDataManager.get(Util.parseStringUUID(entity.getUuid()));
        if (data.getConfig().get(DonorData.ConfigOptions.BOOL_RENDER_CLOAKS_AND_ELYTRAS).getAsBoolean()) {
            Cosmetic cosmetic = data.getSelectedCosmetics().get(CosmeticSlot.BACK);
            if (cosmetic != null) {
                ElytraCosmeticModel elytra = CosmeticsClient.getElytraModels().get(cosmetic);
                if (elytra != null) {
                    this.getContextModel().copyStateTo((EntityModel<T>) elytra);
                    elytra.setAngles((PlayerEntity) entity, f, g, j, k, l);
                    ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.CHEST);
                    VertexConsumer vertex = ItemRenderer.getArmorGlintConsumer(vertices, RenderLayer.getArmorCutoutNoCull(elytra.getTexture()), false, itemStack.hasGlint());
                    elytra.render(matrices, vertex, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
                    matrices.pop();

                    ci.cancel();
                }
            }
        }
    }
}
