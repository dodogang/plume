package net.dodogang.plume.cosmetic;

import net.dodogang.plume.util.Util;

import java.util.HashMap;
import java.util.Map;

public class CosmeticsManager {
    /**
     * Defines which players have which cosmetics. UUID:data
     */
    public static final Map<String, CosmeticPlayerData> LOCAL_DATA = new HashMap<>();

    public static void registerPlayerData(String uuid, Cosmetic... cosmetics) {
        uuid = Util.parseStringUUID(uuid);
        LOCAL_DATA.put(uuid, CosmeticPlayerData.of(uuid, cosmetics));
    }
}
