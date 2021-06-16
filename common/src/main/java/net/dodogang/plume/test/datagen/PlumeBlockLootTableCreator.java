package net.dodogang.plume.test.datagen;

import net.dodogang.plume.Plume;
import net.dodogang.plume.datagen.loottables.AbstractBlockLootTableCreator;

public class PlumeBlockLootTableCreator extends AbstractBlockLootTableCreator {
    @Override
    public void addAll() {
        addDrop(Plume.getTestBlock());
    }

    @Override
    public String getModId() {
        return Plume.MOD_ID;
    }
}
