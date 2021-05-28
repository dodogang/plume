package net.dodogang.plume.cosmetic;

import net.dodogang.plume.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CosmeticsManager {
    /**
     * Defines which players have which cosmetics. UUID:data
     */
    private static final Map<String, CosmeticPlayerData> LOCAL_DATA = new HashMap<>();

    public static void setPlayerData(String uuid, Cosmetic... cosmetics) {
        uuid = Util.parseStringUUID(uuid);
        LOCAL_DATA.put(uuid, CosmeticPlayerData.of(uuid, cosmetics));
    }

    public static CosmeticPlayerData getLocalData(String uuid) {
        return LOCAL_DATA.get(uuid);
    }
    public static CosmeticPlayerData getLocalData(UUID uuid) {
        return CosmeticsManager.getLocalData(Util.parseStringUUID(uuid));
    }

    public static void putLocalData(CosmeticPlayerData data) {
        LOCAL_DATA.put(data.getUuid(), data);
    }
}
