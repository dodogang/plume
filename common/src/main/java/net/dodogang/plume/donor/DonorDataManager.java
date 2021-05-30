package net.dodogang.plume.donor;

import com.google.gson.JsonElement;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.util.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public static void set(String uuid, int patreon, boolean nitro, @Nullable Map<String, JsonElement> config , Cosmetic... cosmetics) {
        DonorDataManager.put(DonorData.of(Util.parseStringUUID(uuid), patreon, nitro, config , cosmetics));
    }

    @NotNull
    public static DonorData get(String uuid) {
        DonorData data = STORED.get(uuid);
        return data == null ? DonorData.getEmpty() : data;
    }
    public static DonorData get(UUID uuid) {
        return DonorDataManager.get(Util.parseStringUUID(uuid));
    }
}
