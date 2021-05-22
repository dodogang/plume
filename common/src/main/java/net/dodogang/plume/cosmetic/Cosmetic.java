package net.dodogang.plume.cosmetic;

import net.minecraft.util.Identifier;

public class Cosmetic {
    public final Identifier id;
    public final CosmeticSlot slot;

    public Cosmetic(Identifier id, CosmeticSlot slot) {
        this.id = id;
        this.slot = slot;
    }

    /**
     * @return Whether this cosmetic is expected to have an attached renderer.
     */
    public boolean hasRenderer() {
        return true;
    }

    @Override
    public String toString() {
        return "Cosmetic{" + "id=" + id + ", slot=" + slot + '}';
    }
}
