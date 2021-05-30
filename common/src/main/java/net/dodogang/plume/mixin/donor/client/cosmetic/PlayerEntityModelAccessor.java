package net.dodogang.plume.mixin.donor.client.cosmetic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityModel.class)
public interface PlayerEntityModelAccessor {
    @Accessor("cape")
    ModelPart getCape();
}
