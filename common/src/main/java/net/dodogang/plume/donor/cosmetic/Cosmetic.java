package net.dodogang.plume.donor.cosmetic;

import net.dodogang.plume.Plume;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class Cosmetic {
    private final Identifier id;
    private final CosmeticSlot slot;

    private final Identifier texture;

    public Cosmetic(Identifier id, CosmeticSlot slot) {
        this.id = id;
        this.slot = slot;

        this.slot.addCosmetic(this);

        this.texture = new Identifier(Plume.MOD_ID, "textures/gui/cosmetics/icon/" + id.getNamespace() + "/" + id.getPath() + ".png");
    }

    public Identifier getId() {
        return this.id;
    }
    public CosmeticSlot getSlot() {
        return this.slot;
    }

    public Identifier getTexture() {
        return this.texture;
    }

    public String getTranslationKey() {
        return Plume.MOD_ID + ".cosmetic." + id.toString();
    }

    @Override
    public String toString() {
        return this.getTranslationKey();
    }

    @Environment(EnvType.CLIENT)
    public void onClick() {
        if (CosmeticSets.MELON_MANGLER.contains(this)) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_ILLUSIONER_MIRROR_MOVE, 0.78F, 0.5F));
        } else if (CosmeticSets.NAUTILUS.contains(this)) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_BOAT_PADDLE_WATER, 2.0F, 1.1F));
        }
    }
}
