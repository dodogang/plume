package net.dodogang.plume.cosmetic;

import net.minecraft.entity.EquipmentSlot;

public enum CosmeticSlot {
    HEAD(EquipmentSlot.HEAD),
    MASK(EquipmentSlot.HEAD),
    CHEST(EquipmentSlot.CHEST),
    LEGS(EquipmentSlot.LEGS),
    FEET(EquipmentSlot.FEET),
    HAND,
    PARTICLE,
    PET;

    /**
     * A base slot, used for cancelling armor rendering.
     */
    protected final EquipmentSlot base;

    CosmeticSlot(EquipmentSlot base) {
        this.base = base;
    }
    CosmeticSlot() {
        this(null);
    }

    public EquipmentSlot getBase() {
        return this.base;
    }

    @Override
    public String toString() {
        return "CosmeticSlot{" + "base=" + base + '}';
    }
}
