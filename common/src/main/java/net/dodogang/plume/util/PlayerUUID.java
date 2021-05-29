package net.dodogang.plume.util;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

import java.util.List;

@SuppressWarnings("unused")
public class PlayerUUID {
    // Oh, my fair lady, if I could escape the wrath of the imposed
    // universal law of priority, I would take that opportunity.
    public static final String AndanteMC        = "50c7e7c5-2407-4102-875f-e4b1f49ea61a";
    public static final String Trikzon          = "d72ef726-e64e-470d-867c-ff8e9b69c751";
    public static final String TinyAtoms        = "26c48243-7229-4246-9c13-afdeb72abcc8";
    public static final String Ceresleepy       = "8838524b-0c25-4dd4-a8ac-2a08f0170685";
    public static final String Mr_Esuoh         = "c92ed179-b1eb-4254-a4f8-590beecd2b0d";
    public static final String ItsBlackGear     = "58c28704-b377-4080-b3cf-e53bc53eda0a";
    public static final String _Ninni           = "2d173722-de6b-4bb8-b21b-b2843cfe395d";
    public static final String FoxShadew        = "0b4e8a09-751b-4e92-a6e6-d0f6078d4e20";

    /**
     * UUID of the client user.
     */
    public static final @Environment(EnvType.CLIENT) String $CLIENT = MinecraftClient.getInstance().getSession().getProfile().getId().toString();
    /**
     * Zeroed-out testing UUID.
     */
    public static final String $BLANK = "00000000-0000-0000-0000-000000000000";

    public static final ImmutableList<String> $TEAM_MEMBERS = ImmutableList.of(
        AndanteMC,
        Trikzon,
        TinyAtoms,
        Ceresleepy,
        Mr_Esuoh,
        ItsBlackGear,
        _Ninni,
        FoxShadew
    );
}
