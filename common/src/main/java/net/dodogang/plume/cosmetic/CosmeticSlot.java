package net.dodogang.plume.cosmetic;

import com.google.common.collect.Lists;
import net.minecraft.entity.EquipmentSlot;

import java.util.List;

public enum CosmeticSlot {
    HAT(EquipmentSlot.HEAD),
    MASK,
    CHEST(EquipmentSlot.CHEST),
    BACK,
    LEGS(EquipmentSlot.LEGS),
    FEET(EquipmentSlot.FEET),
    HAND,
    AURA;

    /**
     * A collection of base slots, used for cancelling armor rendering.
     */
    protected final List<EquipmentSlot> armorRenderCancellers;

    CosmeticSlot(EquipmentSlot... armorRenderCancellers) {
        this.armorRenderCancellers = Lists.newArrayList(armorRenderCancellers);
    }

    public List<EquipmentSlot> getArmorRenderCancellers() {
        return this.armorRenderCancellers;
    }

    @Override
    public String toString() {
        return "CosmeticSlot{" + "base=" + armorRenderCancellers + '}';
    }
}
