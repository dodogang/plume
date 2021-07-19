package net.dodogang.plume.donor.client;

import com.google.common.collect.Lists;
import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.DonorDataManager;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.donor.cosmetic.Cosmetics;
import net.dodogang.plume.util.PlayerUUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Environment(EnvType.CLIENT)
public final class DonorDataManagerClient {
    /**
     * All of this client's available cosmetics.
     */
    private static final List<Cosmetic> AVAILABLE_COSMETICS = new ArrayList<>();

    static {
        boolean isDevEnv = net.dodogang.plume.ash.Environment.isDevelopmentEnvironment();

        // TODO patreon + nitro (networking)
        DonorDataManager.put(DonorData.of(PlayerUUID.$CLIENT, isDevEnv ? 0 : 3, isDevEnv, null));

        if (isDevEnv || PlayerUUID.$TEAM_MEMBERS.contains(PlayerUUID.$CLIENT)) {
            DonorDataManagerClient.addAvailableCosmetics(Cosmetics.ALL_ARRAY);
        }
    }

    // TODO networking + saved config
    public static void setCosmetic(Cosmetic cosmetic) {
        DonorData data = DonorDataManagerClient.getOwn();
        List<Cosmetic> cosmetics = Lists.newArrayList(data.getSelectedCosmetics().values());
        cosmetics.removeIf(c -> c == cosmetic);
        cosmetics.add(cosmetic);

        DonorDataManager.get(PlayerUUID.$CLIENT).setSelectedCosmetics(cosmetics.toArray(new Cosmetic[]{}));
    }
    public static void clearCosmeticSlot(CosmeticSlot slot) {
        DonorData data = DonorDataManagerClient.getOwn();
        List<Cosmetic> cosmetics = Lists.newArrayList(data.getSelectedCosmetics().values());
        cosmetics.removeIf(c -> c.getSlot() == slot);

        DonorDataManager.get(PlayerUUID.$CLIENT).setSelectedCosmetics(cosmetics.toArray(new Cosmetic[]{}));
    }

    public static DonorData getOwn() {
        return DonorDataManager.get(PlayerUUID.$CLIENT);
    }
    public static void setOwn(DonorData data) {
        DonorDataManager.put(data);
    }

    public static void addAvailableCosmetics(Cosmetic... cosmetics) {
        List<Cosmetic> adding = Arrays.asList(cosmetics);
        DonorDataManagerClient.AVAILABLE_COSMETICS.removeAll(adding);
        DonorDataManagerClient.AVAILABLE_COSMETICS.addAll(adding);
    }
    public static List<Cosmetic> getAvailableCosmetics() {
        return DonorDataManagerClient.AVAILABLE_COSMETICS;
    }

}
