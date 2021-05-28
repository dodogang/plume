package net.dodogang.plume.cosmetic;

import net.dodogang.plume.Plume;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class Cosmetic {
    public final Identifier id;
    public final CosmeticSlot slot;

    public final Identifier texture;

    public Cosmetic(Identifier id, CosmeticSlot slot) {
        this.id = id;
        this.slot = slot;

        this.texture = new Identifier(Plume.MOD_ID, "textures/gui/cosmetics/icon/" + id.getNamespace() + "/" + id.getPath() + ".png");
    }

    @Override
    public String toString() {
        return this.getTranslationKey();
    }

    public String getTranslationKey() {
        return Plume.MOD_ID + ".cosmetic." + id.toString();
    }

    @Environment(EnvType.CLIENT)
    public void onClick() {
        if (CosmeticSets.MELON_MANGLER.contains(this)) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0F));
        }
    }
}
