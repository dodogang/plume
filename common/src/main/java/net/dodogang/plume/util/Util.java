package net.dodogang.plume.util;

import java.util.Locale;
import java.util.UUID;

public class Util {
    public static String parseStringUUID(UUID uuid) {
        return Util.parseStringUUID(uuid.toString());
    }
    public static String parseStringUUID(String uuid) {
        return uuid.toLowerCase(Locale.ROOT);
    }
}
