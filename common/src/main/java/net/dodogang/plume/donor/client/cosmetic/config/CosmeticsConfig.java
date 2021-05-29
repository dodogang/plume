package net.dodogang.plume.donor.client.cosmetic.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import net.dodogang.plume.Plume;
import net.dodogang.plume.ash.Environment;
import net.dodogang.plume.donor.DonorData;
import net.dodogang.plume.donor.cosmetic.*;
import net.dodogang.plume.donor.client.DonorDataManagerClient;
import net.dodogang.plume.util.PlayerUUID;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CosmeticsConfig {
    private static final File FILE = Environment.getConfigDir().resolve(Plume.MOD_ID + "/cosmetics.json").toFile();

    static {
        CosmeticsConfig.load();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void save() {
        JsonObject json = new JsonObject();
        DonorData data = DonorDataManagerClient.getOwn();

        data.getSelectedCosmetics().forEach((cosmeticSlot, cosmetic) -> {
            JsonObject cosmeticJson = new JsonObject();
            cosmeticJson.addProperty("id", cosmetic.getId().toString());
            json.add(cosmeticSlot.getId(), cosmeticJson);
        });

        FILE.getParentFile().mkdirs();
        try (PrintWriter out = new PrintWriter(FILE)) {
            StringWriter string = new StringWriter();

            JsonWriter jsonWriter = new JsonWriter(string);
            jsonWriter.setLenient(true);
            jsonWriter.setIndent("  ");

            Streams.write(json, jsonWriter);
            out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            String string = new String(Files.readAllBytes(FILE.toPath()));
            if (!string.isEmpty()) {
                JsonObject json = (JsonObject) new JsonParser().parse(string);
                List<Cosmetic> cosmetics = new ArrayList<>();

                for (CosmeticSlot slot : CosmeticSlot.values()) {
                    JsonObject cosmeticJson = json.getAsJsonObject(slot.getId());
                    if (cosmeticJson != null) {
                        cosmetics.add(Cosmetics.get(Identifier.tryParse(cosmeticJson.get("id").getAsString())));
                    }
                }

                DonorData.replace(PlayerUUID.$CLIENT, cosmetics.toArray(new Cosmetic[]{}));
            }
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            Plume.log(Level.ERROR, "Could not load saved cosmetics!");
            e.printStackTrace();
        }
    }
}
