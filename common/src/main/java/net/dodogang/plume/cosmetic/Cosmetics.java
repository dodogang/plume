package net.dodogang.plume.cosmetic;

import net.dodogang.plume.Plume;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Cosmetics {
    public static final List<Cosmetic> ALL = new ArrayList<>();

    /*
     * MELON MANGLER
     */

    public static final Cosmetic MELON_MANGLER_HAT = register("melon_mangler_hat", CosmeticSlot.HAT);
    public static final Cosmetic MELON_MANGLER_MASK = register("melon_mangler_mask", CosmeticSlot.MASK);
    public static final Cosmetic MELON_MANGLER_CHEST = register("melon_mangler_chest", CosmeticSlot.CHEST);

    /**
     * TESTING
     */

    public static final Cosmetic AURA = register("aura", CosmeticSlot.TICKER);

    public static Cosmetic register(Cosmetic cosmetic) {
        Cosmetics.ALL.add(cosmetic);
        return cosmetic;
    }
    public static Cosmetic register(String id, CosmeticSlot slot) {
        return register(new Cosmetic(new Identifier(Plume.MOD_ID, id), slot));
    }

    public static List<Cosmetic> all() {
        return Cosmetics.ALL;
    }
    public static Cosmetic[] arrayAll() {
        return Cosmetics.all().toArray(new Cosmetic[]{});
    }
}
