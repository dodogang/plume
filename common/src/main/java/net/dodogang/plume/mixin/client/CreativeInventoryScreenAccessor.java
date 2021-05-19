package net.dodogang.plume.mixin.client;

import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.util.Identifier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreativeInventoryScreen.class)
public interface CreativeInventoryScreenAccessor {
    @Accessor("TEXTURE")
    static Identifier getTexture() {
        throw new AssertionError();
    }
}
