package net.dodogang.plume.cosmetic;

import net.dodogang.plume.Plume;
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

    /*
     * NAUTILUS
     */

    public static final Cosmetic NAUTILUS_HAT = createNautilus(CosmeticSlot.HAT);
    public static final Cosmetic NAUTILUS_BACK = createNautilus(CosmeticSlot.BACK);
    private static Cosmetic createNautilus(CosmeticSlot slot) {
        Cosmetic cosmetic = new Cosmetic(new Identifier(Plume.MOD_ID, "nautilus_" + slot.getId()), slot);
        CosmeticSets.NAUTILUS.add(cosmetic);
        return register(cosmetic);
    }

    public static final Cosmetic YELLOW_NAUTILUS_HAT = createYellowNautilus(CosmeticSlot.HAT);
    public static final Cosmetic YELLOW_NAUTILUS_BACK = createYellowNautilus(CosmeticSlot.BACK);
    private static Cosmetic createYellowNautilus(CosmeticSlot slot) {
        Cosmetic cosmetic = new Cosmetic(new Identifier(Plume.MOD_ID, "yellow_nautilus_" + slot.getId()), slot);
        CosmeticSets.NAUTILUS.add(cosmetic);
        return register(cosmetic);
    }

    public static final Cosmetic ORANGE_NAUTILUS_HAT = createOrangeNautilus(CosmeticSlot.HAT);
    public static final Cosmetic ORANGE_NAUTILUS_BACK = createOrangeNautilus(CosmeticSlot.BACK);
    private static Cosmetic createOrangeNautilus(CosmeticSlot slot) {
        Cosmetic cosmetic = new Cosmetic(new Identifier(Plume.MOD_ID, "orange_nautilus_" + slot.getId()), slot);
        CosmeticSets.NAUTILUS.add(cosmetic);
        return register(cosmetic);
    }

    public static Cosmetic register(Cosmetic cosmetic) {
        cosmetic.slot.addCosmetic(cosmetic);
        return cosmetic;
    }
}
