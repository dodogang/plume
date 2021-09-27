package net.dodogang.plume.block.enums;

import net.minecraft.util.StringIdentifiable;

import java.util.Locale;

public enum TripleBlockPart implements StringIdentifiable {
    UPPER,
    MIDDLE,
    LOWER;

    @Override
    public String toString() {
        return this.asString();
    }

    @Override
    public String asString() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
