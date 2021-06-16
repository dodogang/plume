package net.dodogang.plume.mixin.datagen;

import net.dodogang.plume.Plume;
import net.dodogang.plume.datagen.DataMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedMixin")
@Mixin({
    net.minecraft.client.main.Main.class,
    net.minecraft.server.Main.class
})
public class DataGenMixin {
    @Inject(method = "main", at = @At("HEAD"), cancellable = true)
    private static void onDataMain(CallbackInfo ci) {
        boolean data = Boolean.parseBoolean(System.getProperty(Plume.MOD_ID + ".datagen"));
        if (data) {
            String path = System.getProperty(Plume.MOD_ID + ".datagen.path");

            String[] paths = path == null ? new String[0] : path.split(";");

            List<String> args = new ArrayList<>();
            args.add("-client");
            args.add("-server");
            String pathOptName = "-output";
            for (String pth : paths) {
                args.add(pathOptName);
                args.add(pth);
                pathOptName = "-extra";
            }

            try {
                DataMain.main(args.toArray(new String[0]));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ci.cancel();
            }
        }
    }
}
