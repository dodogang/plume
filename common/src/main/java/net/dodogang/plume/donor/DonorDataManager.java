package net.dodogang.plume.donor;

import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DonorDataManager {
    /**
     * Defines which players have which cosmetics according to this game instance.
     */
    private static final Map<String, DonorData> STORED = new HashMap<>();

    public static void put(DonorData data) {
        STORED.put(data.getUuid(), data);
    }

    public static void set(String uuid, int patreon, boolean nitro, Cosmetic... cosmetics) {
        DonorDataManager.put(DonorData.of(Util.parseStringUUID(uuid), patreon, nitro, cosmetics));
    }

    public static DonorData get(String uuid) {
        return STORED.get(uuid);
    }
    public static DonorData get(UUID uuid) {
        return DonorDataManager.get(Util.parseStringUUID(uuid));
    }
}
