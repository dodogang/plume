package net.dodogang.plume.cosmetic;

import net.dodogang.plume.Plume;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.Identifier;

public class Cosmetics {
    /*
     * MELON MANGLER
     */

    public static final Cosmetic MELON_MANGLER_HAT = register("melon_mangler_hat", CosmeticSlot.HAT);
    public static final Cosmetic MELON_MANGLER_MASK = register("melon_mangler_mask", CosmeticSlot.MASK);
    public static final Cosmetic MELON_MANGLER_CHEST = register("melon_mangler_chest", CosmeticSlot.CHEST);

    /**
     * TESTING
     */

    public static final Cosmetic AURA = new TickingCosmetic(new Identifier(Plume.MOD_ID, "aura"), CosmeticSlot.AURA, (world, player) -> world.addParticle(new DustParticleEffect(1.0f, 0.0f, 1.0f, 1.0f), player.getParticleX(0.7d), player.getRandomBodyY(), player.getParticleZ(0.7d), 0.075d, 0.075d, 0.075d));

    private static Cosmetic register(String id, CosmeticSlot slot) {
        return new Cosmetic(new Identifier(Plume.MOD_ID, id), slot);
    }
}
