package net.dodogang.plume.cosmetic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TickingCosmetic extends Cosmetic {
    private final Ticker ticker;

    public TickingCosmetic(Identifier id, CosmeticSlot slot, Ticker ticker) {
        super(id, slot);
        this.ticker = ticker;
    }

    @Override
    public boolean hasRenderer() {
        return false;
    }

    @Environment(EnvType.CLIENT)
    public boolean shouldTick(PlayerEntity player) {
        MinecraftClient client = MinecraftClient.getInstance();
        return !client.options.getPerspective().isFirstPerson() || client.player != (player);
    }
    public void tick(World world, PlayerEntity player) {
        this.ticker.tick(world, player);
    }

    @FunctionalInterface
    protected interface Ticker {
        void tick(World world, PlayerEntity player);
    }
}
