package net.dodogang.plume.cosmetic;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CosmeticPlayerData {
    private final @NotNull UUID playerUUID;
    private final @NotNull Map<CosmeticSlot, Cosmetic> cosmeticToSlot;

    private CosmeticPlayerData(UUID playerUUID, Map<CosmeticSlot, Cosmetic> cosmeticToSlot) {
        this.playerUUID = playerUUID;
        this.cosmeticToSlot = cosmeticToSlot;
    }

    public static CosmeticPlayerData of(UUID playerUUID, Cosmetic... cosmetics) {
        Map<CosmeticSlot, Cosmetic> cosmeticToSlot = new HashMap<>();
        for (Cosmetic cosmetic : cosmetics) {
            cosmeticToSlot.put(cosmetic.slot, cosmetic);
        }
        return new CosmeticPlayerData(playerUUID, cosmeticToSlot);
    }

    @NotNull
    public Map<CosmeticSlot, Cosmetic> getCosmetics() {
        return this.cosmeticToSlot;
    }

    @Override
    public String toString() {
        return "CosmeticPlayerData{" + "playerUUID=" + playerUUID + ", cosmetics=" + cosmeticToSlot + '}';
    }
}
