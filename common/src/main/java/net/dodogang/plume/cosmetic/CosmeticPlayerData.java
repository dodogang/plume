package net.dodogang.plume.cosmetic;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CosmeticPlayerData {
    private final @NotNull String uuid;
    private final @NotNull Map<CosmeticSlot, Cosmetic> active;

    private CosmeticPlayerData(@NotNull String uuid, @NotNull Map<CosmeticSlot, Cosmetic> active) {
        this.uuid = uuid;
        this.active = active;
    }

    public static CosmeticPlayerData of(@NotNull String uuid, @NotNull Cosmetic... cosmetics) {
        Map<CosmeticSlot, Cosmetic> active = new HashMap<>();
        for (Cosmetic cosmetic : cosmetics) {
            active.put(cosmetic.slot, cosmetic);
        }

        return new CosmeticPlayerData(uuid, active);
    }

    @NotNull
    public String getUuid() {
        return this.uuid;
    }
    @NotNull
    public Map<CosmeticSlot, Cosmetic> getCosmetics() {
        return this.active;
    }

    @Override
    public String toString() {
        return "CosmeticPlayerData{" + "uuid='" + uuid + '\'' + ", active=" + active + '}';
    }
}
