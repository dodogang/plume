package net.dodogang.plume.mixin.cosmetic.client;

import net.dodogang.plume.cosmetic.client.CosmeticsManagerClient;
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
    private void cancelRender(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, T entity, EquipmentSlot equipmentSlot, int i, A bipedEntityModel, CallbackInfo ci) {
        CosmeticsManagerClient.cancelArmorRender(entity.getUuid(), equipmentSlot, ci);
    }
}
