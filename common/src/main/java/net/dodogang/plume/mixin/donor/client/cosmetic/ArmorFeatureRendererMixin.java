package net.dodogang.plume.mixin.donor.client.cosmetic;

import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
    @SuppressWarnings("unused")
    private ArmorFeatureRendererMixin(FeatureRendererContext<T, M> ctx) {
        super(ctx);
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void cancelRender(MatrixStack matrices, VertexConsumerProvider vertices, T entity, EquipmentSlot equipmentSlot, int i, A model, CallbackInfo ci) {
        DonorData data = DonorDataManager.get(Util.parseStringUUID(entity.getUuid()));
        for (CosmeticSlot slot : data.getSelectedCosmetics().keySet()) {
            if (slot.getArmorRenderCancellers().contains(equipmentSlot)) {
                ci.cancel();
                return;
            }
        }
    }
}
