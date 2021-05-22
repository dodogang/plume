package net.dodogang.plume.mixin.client.cosmetic;

import net.dodogang.plume.cosmetic.client.CosmeticsManagerClient;
import net.dodogang.plume.cosmetic.client.render.CosmeticFeatureRenderer;
import net.dodogang.plume.cosmetic.Cosmetic;
import net.dodogang.plume.cosmetic.CosmeticPlayerData;
import net.dodogang.plume.cosmetic.CosmeticSlot;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    @SuppressWarnings("unused")
    private PlayerEntityRendererMixin(EntityRenderDispatcher dispatcher, PlayerEntityModel<AbstractClientPlayerEntity> model, float thin) {
        super(dispatcher, model, thin);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Inject(method = "render", at = @At("HEAD"))
    public void onRender(AbstractClientPlayerEntity entity, float f, float g, MatrixStack matrices, VertexConsumerProvider vertices, int i, CallbackInfo ci) {
        if (!entity.isSpectator()) {
            CosmeticPlayerData cosmetics = CosmeticsManagerClient.LOCAL_DATA.get(entity.getUuid());
            if (cosmetics != null) {
                Map<Cosmetic, FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>> cache = CosmeticsManagerClient.FEATURES_CACHE.get(entity.getUuid());
                if (cache == null) {
                    // create base of cache if invalid
                    CosmeticsManagerClient.FEATURES_CACHE.put(entity.getUuid(), new HashMap<>());
                } else {
                    Map<CosmeticSlot, Cosmetic> active = cosmetics.getCosmetics();
                    active.forEach((slot, cosmetic) -> {
                        if (cosmetic.hasRenderer()) {
                            // instantiate and store feature renderer into features nd cache
                            FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> renderer = cache.get(cosmetic);
                            if (renderer == null) {
                                renderer = CosmeticsManagerClient.RENDERER_MAP
                                    .get(cosmetic)
                                    .apply(PlayerEntityRenderer.class.cast(this), (FeatureRendererContext) this);

                                cache.put(cosmetic, renderer);
                                this.addFeature(renderer);
                            }
                        }
                    });

                    // remove cosmetic from features list and cache if no longer present
                    this.features.removeIf(renderer -> {
                        if (renderer instanceof CosmeticFeatureRenderer) {
                            Cosmetic rendererCosmetic = ((CosmeticFeatureRenderer) renderer).getCosmetic();
                            if (!active.containsValue(rendererCosmetic)) {
                                cache.remove(rendererCosmetic);
                                return true;
                            }
                        }

                        return false;
                    });
                }
            }
        }
    }
}
