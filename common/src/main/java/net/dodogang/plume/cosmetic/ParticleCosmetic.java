package net.dodogang.plume.cosmetic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ParticleCosmetic extends Cosmetic {
    private final ParticleCreator particleCreator;

    public ParticleCosmetic(Identifier id, ParticleCreator particleCreator) {
        super(id, CosmeticSlot.PARTICLE);
        this.particleCreator = particleCreator;
    }

    public void spawnParticle(World world, PlayerEntity player) {
        this.particleCreator.spawnParticle(world, player);
    }

    @FunctionalInterface
    protected interface ParticleCreator {
        void spawnParticle(World world, PlayerEntity player);
    }
}
