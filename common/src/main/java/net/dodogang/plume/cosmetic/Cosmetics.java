package net.dodogang.plume.cosmetic;

import net.minecraft.util.Identifier;

public class Cosmetics {
    /*
     * MELON MANGLER
     */

    public static final Cosmetic MELON_MANGLER_HAT = createMelonMangler(CosmeticSlot.HAT);
    public static final Cosmetic MELON_MANGLER_MASK = createMelonMangler(CosmeticSlot.MASK);
    public static final Cosmetic MELON_MANGLER_CHEST = createMelonMangler(CosmeticSlot.CHEST);
    public static final Cosmetic MELON_MANGLER_BACK = createMelonMangler(CosmeticSlot.BACK);
    public static final Cosmetic MELON_MANGLER_HAND = createMelonMangler(CosmeticSlot.HAND);
    public static final Cosmetic MELON_MANGLER_FEET = createMelonMangler(CosmeticSlot.FEET);
    public static final Cosmetic MELON_MANGLER_TICKER = createMelonMangler(CosmeticSlot.TICKER);
    private static Cosmetic createMelonMangler(CosmeticSlot slot) {
        Cosmetic cosmetic = new Cosmetic(new Identifier("marbles", "melon_mangler_" + slot.getId()), slot);
        CosmeticSets.MELON_MANGLER.add(cosmetic);
        return register(cosmetic);
    }

    public static Cosmetic register(Cosmetic cosmetic) {
        cosmetic.slot.addCosmetic(cosmetic);
        return cosmetic;
    }
}
