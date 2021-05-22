package net.dodogang.plume.cosmetic;

import com.google.common.collect.Lists;
import net.minecraft.entity.EquipmentSlot;

import java.util.List;

public enum CosmeticSlot {
    HAT(EquipmentSlot.HEAD),
    MASK(EquipmentSlot.HEAD),
    CHEST(EquipmentSlot.CHEST),
    BACK,
    LEGS(EquipmentSlot.LEGS),
    FEET(EquipmentSlot.FEET),
    HAND,
    AURA;

    /**
     * A collection of base slots, used for cancelling armor rendering.
     */
    protected final List<EquipmentSlot> slotsToCancelRender;

    CosmeticSlot(EquipmentSlot... slotsToCancelRender) {
        this.slotsToCancelRender = Lists.newArrayList(slotsToCancelRender);
    }

    public List<EquipmentSlot> getSlotsToCancelRender() {
        return this.slotsToCancelRender;
    }

    @Override
    public String toString() {
        return "CosmeticSlot{" + "base=" + slotsToCancelRender + '}';
    }
}
