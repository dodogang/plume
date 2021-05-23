package net.dodogang.plume.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@SuppressWarnings("unused")
public class PlayerUUID {
    public static final String AndanteMC = "50c7e7c5-2407-4102-875f-e4b1f49ea61a";
    public static final String TinyAtoms = "26c48243-7229-4246-9c13-afdeb72abcc8";

    /**
     * UUID of the client user.
     */
    @Environment(EnvType.CLIENT)
    public static final String $CLIENT = MinecraftClient.getInstance().getSession().getProfile().getId().toString();
    /**
     * Zeroed-out testing UUID.
     */
    public static final String $BLANK = "00000000-0000-0000-0000-000000000000";
}
