package net.dodogang.plume.util;

import net.dodogang.plume.client.PlumeClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.UUID;

public class Util {
    public static final @Environment(EnvType.CLIENT) Identifier DONOR_BADGE = PlumeClient.texture("gui/donor_badge");
    public static final @Environment(EnvType.CLIENT) Identifier DONOR_BADGE_TEAM = PlumeClient.texture("gui/donor_badge_team");

    public static String parseStringUUID(UUID uuid) {
        return Util.parseStringUUID(uuid.toString());
    }
    public static String parseStringUUID(String uuid) {
        return uuid.toLowerCase(Locale.ROOT);
    }
}
