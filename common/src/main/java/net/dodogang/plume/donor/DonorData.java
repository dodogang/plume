package net.dodogang.plume.donor;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import net.dodogang.plume.donor.client.DonorDataManagerClient;
import net.dodogang.plume.donor.cosmetic.Cosmetic;
import net.dodogang.plume.donor.cosmetic.CosmeticSlot;
import net.dodogang.plume.donor.cosmetic.Cosmetics;
import net.dodogang.plume.util.PlayerUUID;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonorData {
    private final @NotNull String uuid;

    private final int patreonTier;
    private final boolean nitro;
    private final @NotNull Map<String, JsonElement> config;
    private final @NotNull Map<CosmeticSlot, Cosmetic> selectedCosmetics;

    private static final DonorData EMPTY = DonorData.of(PlayerUUID.$BLANK, 0, false, null);
    private static ImmutableMap<String, JsonElement> DEFAULT_CONFIG = null;

    private DonorData(@NotNull String uuid, int patreonTier, boolean nitro, @Nullable Map<String, JsonElement> config, @Nullable Map<CosmeticSlot, Cosmetic> selectedCosmetics) {
        this.uuid = uuid;

        this.patreonTier = patreonTier;
        this.nitro = nitro;
        this.selectedCosmetics = selectedCosmetics == null ? new HashMap<>() : selectedCosmetics;
        this.config = config == null ? new HashMap<>(DonorData.getDefaultConfig()) : config;
    }

    public static DonorData of(String uuid, int patreon, boolean nitro, @Nullable Map<String, JsonElement> config, Cosmetic... selectedCosmetics) {
        DonorData data = new DonorData(uuid, patreon, nitro, config == null ? new HashMap<>(DonorData.getDefaultConfig()) : config, null);
        data.setSelectedCosmetics(selectedCosmetics);

        return data;
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

    @NotNull
    public Map<String, JsonElement> getConfig() {
        return this.config;
    }
    @NotNull
    public JsonElement getConfig(String key) {
        JsonElement value = this.getConfig().get(key);
        return value == null ? new JsonPrimitive(false) : value;
    }
    public static ImmutableMap<String, JsonElement> getDefaultConfig() {
        if (DEFAULT_CONFIG == null) {
            DEFAULT_CONFIG = ImmutableMap.of(
                ConfigOptions.BOOL_RENDER_CAPES_AND_ELYTRAS, new JsonPrimitive(false)
            );
        }

        return DEFAULT_CONFIG;
    }
    public void setConfig(ImmutableMap<String, JsonElement> config) {
        this.config.clear();
        config.forEach(this.config::put);
    }

    @NotNull
    public Map<CosmeticSlot, Cosmetic> getSelectedCosmetics() {
        return this.selectedCosmetics;
    }
    public void setSelectedCosmetics(Cosmetic... cosmetics) {
        this.selectedCosmetics.clear();
        for (Cosmetic cosmetic : cosmetics) {
            this.selectedCosmetics.put(cosmetic.getSlot(), cosmetic);
        }
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        // cosmetics
        JsonObject cosmeticsJson = new JsonObject();
        this.getSelectedCosmetics().forEach((cosmeticSlot, cosmetic) -> {
            JsonObject cosmeticJson = new JsonObject();
            cosmeticJson.addProperty("id", cosmetic.getId().toString());
            cosmeticsJson.add(cosmeticSlot.getId(), cosmeticJson);
        });
        json.add("cosmetics", cosmeticsJson);

        // other configuration
        JsonObject configJson = new JsonObject();
        this.config.forEach(configJson::add);
        json.add("config", configJson);

        return json;
    }
    public void fromJson(JsonObject json) {
        // cosmetics
        JsonObject cosmeticsJson = json.getAsJsonObject("cosmetics");
        List<Cosmetic> cosmetics = new ArrayList<>();

        for (CosmeticSlot slot : CosmeticSlot.values()) {
            JsonObject cosmeticJson = cosmeticsJson.getAsJsonObject(slot.getId());
            if (cosmeticJson != null) {
                cosmetics.add(Cosmetics.get(Identifier.tryParse(cosmeticJson.get("id").getAsString())));
            }
        }

        this.setSelectedCosmetics(cosmetics.toArray(new Cosmetic[]{}));

        // other configuration
        JsonObject configJson = json.getAsJsonObject("config");
        this.config.clear();
        DonorData.getDefaultConfig().forEach((key, value) -> this.config.put(key, configJson.get(key)));
    }

    public static DonorData getEmpty() {
        return DonorData.EMPTY.copy();
    }
    public DonorData copy() {
        return new DonorData(this.uuid, this.patreonTier, this.nitro, this.config, this.selectedCosmetics);
    }

    @Override
    public String toString() {
        return "DonorData{" + "uuid='" + uuid + '\'' + ", patreonTier=" + patreonTier + ", nitro=" + nitro + ", config=" + config + ", selectedCosmetics=" + selectedCosmetics + '}';
    }

    public static class ConfigOptions {
        public static final String BOOL_RENDER_CAPES_AND_ELYTRAS = "render_capes_and_elytras";
    }
}
