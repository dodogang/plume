package net.dodogang.plume.donor.client.cosmetic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
@FunctionalInterface
public interface CosmeticTicker {
    void tick(World world, LivingEntity player);
}
