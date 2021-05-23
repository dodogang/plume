package net.dodogang.plume.cosmetic;

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

    public void tick(World world, PlayerEntity player) {
        this.ticker.tick(world, player);
    }

    @FunctionalInterface
    protected interface Ticker {
        void tick(World world, PlayerEntity player);
    }
}
