package net.dodogang.plume.cosmetic;

import com.google.common.collect.Lists;
import net.dodogang.plume.Plume;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.*;

public enum CosmeticSlot {
    HAT("hat", EquipmentSlot.HEAD),
    MASK("mask"),
    CHEST("chest", EquipmentSlot.CHEST),
    BACK("back"),
    LEGS("legs", EquipmentSlot.LEGS),
    FEET("feet", EquipmentSlot.FEET),
    HAND("hand"),
    TICKER("ticker");

    /**
     * This slot's simple id.
     */
    protected final String id;

    /**
     * This slot's displayable text.
     */
    protected final Text displayText;

    /**
     * A list of this slot's assigned cosmetics.
     */
    protected final List<Cosmetic> cosmetics = new LinkedList<>();

    /**
     * A collection of base slots, used for cancelling armor rendering.
     */
    protected final List<EquipmentSlot> armorRenderCancellers;

    CosmeticSlot(String id, EquipmentSlot... armorRenderCancellers) {
        this.id = id;
        this.displayText = new TranslatableText(Plume.MOD_ID + ".cosmeticSlot." + id);
        this.armorRenderCancellers = Lists.newArrayList(armorRenderCancellers);
    }

    public String getId() {
        return this.id;
    }
    public Text getDisplayText() {
        return this.displayText;
    }
    public List<Cosmetic> getCosmetics() {
        return new LinkedList<>(this.cosmetics);
    }
    public List<EquipmentSlot> getArmorRenderCancellers() {
        return this.armorRenderCancellers;
    }

    public void addCosmetic(Cosmetic cosmetic) {
        this.cosmetics.add(cosmetic);
    }

    @Environment(EnvType.CLIENT)
    public static Map<CosmeticSlot, List<Cosmetic>> createSimpleMap() {
        Map<CosmeticSlot, List<Cosmetic>> map = new HashMap<>();
        for (CosmeticSlot value : CosmeticSlot.values()) {
            map.put(value, new ArrayList<>());
        }

        return map;
    }

    @Override
    public String toString() {
        return Plume.MOD_ID + ".cosmeticSlot." + this.id;
    }
}
