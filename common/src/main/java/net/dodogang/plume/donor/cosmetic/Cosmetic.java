package net.dodogang.plume.donor.cosmetic;

import net.dodogang.plume.Plume;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class Cosmetic {
    private final Identifier id;
    private final CosmeticSlot slot;

    private final String descriptionKey;
    private final Identifier texture;

    public Cosmetic(Identifier id, CosmeticSlot slot) {
        this.id = id;

        this.slot = slot;
        this.slot.addCosmetic(this);

        this.descriptionKey = String.format("%s.cosmetic.%s.description", Plume.MOD_ID, this.id);
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
        return String.format("%s.cosmetic.%s", Plume.MOD_ID, this.id);
    }

    @Environment(EnvType.CLIENT)
    public boolean hasDescription() {
        return I18n.hasTranslation(this.getDescriptionKey());
    }
    public String getDescriptionKey() {
        return this.descriptionKey;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    @Environment(EnvType.CLIENT)
    public void onClick() {
        if (CosmeticSets.MELON_MANGLER.contains(this)) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_ILLUSIONER_MIRROR_MOVE, 0.78F, 0.5F));
        } else if (CosmeticSets.NAUTILUS.contains(this)) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_BOAT_PADDLE_WATER, 2.0F, 2.0F));
        } else if (CosmeticSets.VAGABOND.contains(this)) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 0.8F));
        }
    }
}
