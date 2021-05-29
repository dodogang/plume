package net.dodogang.plume.donor;

import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.util.PlayerUUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DonorData {
    private final @NotNull String uuid;

    private final int patreonTier;
    private final boolean nitro;
    private final @NotNull Map<CosmeticSlot, Cosmetic> selectedCosmetics;

    private DonorData(@NotNull String uuid, int patreonTier, boolean nitro, @Nullable Map<CosmeticSlot, Cosmetic> selectedCosmetics) {
        this.uuid = uuid;

        this.patreonTier = patreonTier;
        this.nitro = nitro;
        this.selectedCosmetics = selectedCosmetics == null ? new HashMap<>() : selectedCosmetics;
    }

    public static DonorData of(String uuid, int patreon, boolean nitro, Cosmetic... cosmetics) {
        DonorData data = new DonorData(uuid, patreon, nitro, null);
        data.setSelectedCosmetics(cosmetics);

        return data;
    }

    public static void replace(String uuid, Cosmetic... cosmetics) {
        DonorDataManager.get(uuid).setSelectedCosmetics(cosmetics);
    }
    @Environment(EnvType.CLIENT)
    public static void replace(Cosmetic... cosmetics) {
        DonorData.replace(PlayerUUID.$CLIENT, cosmetics);
    }

    public String getUuid() {
        return this.uuid;
    }

    public int getPatreonTier() {
        return this.patreonTier;
    }
    public boolean isPatron() {
        return this.getPatreonTier() >= 1;
    }

    public boolean isNitro() {
        return this.nitro;
    }

    public Map<CosmeticSlot, Cosmetic> getSelectedCosmetics() {
        return this.selectedCosmetics;
    }
    public void setSelectedCosmetics(Cosmetic... cosmetics) {
        this.selectedCosmetics.clear();
        for (Cosmetic cosmetic : cosmetics) {
            this.selectedCosmetics.put(cosmetic.getSlot(), cosmetic);
        }
    }

    @Override
    public String toString() {
        return "DonorData{" + "uuid='" + uuid + '\'' + ", patreonTier=" + patreonTier + ", nitro=" + nitro + ", selectedCosmetics=" + selectedCosmetics + '}';
    }
}
