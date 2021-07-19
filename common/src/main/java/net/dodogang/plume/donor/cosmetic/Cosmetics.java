package net.dodogang.plume.donor.cosmetic;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.Plume;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class Cosmetics {
    private static final Map<Identifier, Cosmetic> REGISTRY = new HashMap<>();
    private static final ImmutableList.Builder<Cosmetic> ALL_BUILDER = new ImmutableList.Builder<>();

    /*
     * MELON MANGLER SET
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
     * NAUTILUS SET
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

    /*
     * VAGABOND SET
     */

    public static final Cosmetic VAGABOND_HAT = createVagabond(CosmeticSlot.HAT);
    public static final Cosmetic VAGABOND_BACK = createVagabond(CosmeticSlot.BACK);
    public static final Cosmetic VAGABOND_FEET = createVagabond(CosmeticSlot.FEET);
    private static Cosmetic createVagabond(CosmeticSlot slot) {
        Cosmetic cosmetic = new Cosmetic(new Identifier(Plume.MOD_ID, "vagabond_" + slot.getId()), slot);
        CosmeticSets.VAGABOND.add(cosmetic);
        return register(cosmetic);
    }

    public static Cosmetic register(Cosmetic cosmetic) {
        REGISTRY.put(cosmetic.getId(), cosmetic);
        ALL_BUILDER.add(cosmetic);
        return cosmetic;
    }

    public static final ImmutableList<Cosmetic> ALL = ALL_BUILDER.build();
    public static final Cosmetic[] ALL_ARRAY = ALL.toArray(new Cosmetic[]{});

    @Nullable
    public static Cosmetic get(Identifier id) {
        return REGISTRY.get(id);
    }
}
