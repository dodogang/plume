package net.dodogang.plume.donor.client.cosmetic.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import net.dodogang.plume.Plume;
import net.dodogang.plume.ash.Environment;
import net.dodogang.plume.donor.client.DonorDataManagerClient;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;

public class CosmeticsConfig {
    private static final File FILE = Environment.getConfigDir().resolve(Plume.MOD_ID + "/cosmetics.json").toFile();

    static {
        CosmeticsConfig.load();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void save() {
        FILE.getParentFile().mkdirs();
        try (PrintWriter out = new PrintWriter(FILE)) {
            StringWriter string = new StringWriter();

            JsonWriter jsonWriter = new JsonWriter(string);
            jsonWriter.setLenient(true);
            jsonWriter.setIndent("  ");

            Streams.write(DonorDataManagerClient.getOwn().toJson(), jsonWriter);
            out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            String string = new String(Files.readAllBytes(FILE.toPath()));
            if (!string.isEmpty()) {
                DonorDataManagerClient.getOwn().fromJson((JsonObject) new JsonParser().parse(string));
            }
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            Plume.log(Level.ERROR, "Could not load saved cosmetics configuration! Defaulting...");
            CosmeticsConfig.save();
        }
    }
}
