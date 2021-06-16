package net.dodogang.plume.datagen.models;

import net.dodogang.plume.datagen.models.stategen.StateGen;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.BiConsumer;
import java.util.function.Function;

/*import static net.dodogang.plume.datagen.models.modelgen.InheritingModelGen.*;
import static net.dodogang.plume.datagen.models.modelgen.ParticleOnlyModelGen.*;
import static net.dodogang.plume.datagen.models.stategen.helper.BuildingBlocksStateHelper.*;
import static net.dodogang.plume.datagen.models.stategen.helper.InteractiveBlocksStateHelper.*;
import static net.dodogang.plume.datagen.models.stategen.helper.SimpleBlocksStateHelper.*;*/

@SuppressWarnings("unused")
public final class BlockStateTable {
    private static BiConsumer<Block, StateGen> consumer;

    public static void registerBlockStates(BiConsumer<Block, StateGen> c) {
        consumer = c;
        BlockStateTable.addAll();
    }

    /**
     * Mixin to add.
     */
    protected static void addAll() {}

    /*private static void registerWoodBlocks(WoodBlocks blocks) {
        register(blocks.PLANKS, block -> simple(name(block, "block/%s"), cubeAll(name(block, "block/%s"))));
        register(blocks.SAPLING, block -> simple(name(block, "block/%s"), cross(name(block, "block/%s"))));
        register(blocks.POTTED_SAPLING, block -> simple(name(block, "block/%s"), flowerPotCross(name(blocks.SAPLING, "block/%s"))));
        register(blocks.LOG, block -> axisRotated(name(block, "block/%s"), cubeColumn(name(block, "block/%s_top"), name(block, "block/%s"))));
        register(blocks.STRIPPED_LOG, block -> axisRotated(name(block, "block/%s"), cubeColumn(name(block, "block/%s_top"), name(block, "block/%s"))));
        register(blocks.WOOD, block -> axisRotated(name(block, "block/%s"), cubeAll(name(block, "block/%s_log", "_wood"))));
        register(blocks.STRIPPED_WOOD, block -> axisRotated(name(block, "block/%s"), cubeAll(name(block, "block/%s_log", "_wood"))));
        register(blocks.LEAVES, block -> simple(name(block, "block/%s"), leaves(name(block, "block/%s"))));
        register(blocks.SLAB, block -> slabAll(name(block, "block/%s"), name(block, "block/%s_planks", "_slab"), name(block, "block/%s_planks", "_slab")));
        register(blocks.STAIRS, block -> stairsAll(name(block, "block/%s"), name(block, "block/%s_planks", "_stairs")));
        register(blocks.FENCE, block -> fence(name(block, "block/%s"), name(block, "block/%s_planks", "_fence")));
        register(blocks.DOOR, block -> door(name(block, "block/%s")));
        register(blocks.TRAPDOOR, block -> trapdoor(name(block, "block/%s")));
        register(blocks.FENCE_GATE, block -> fenceGate(name(block, "block/%s"), name(block, "block/%s_planks", "_fence_gate")));
        register(blocks.BUTTON, block -> button(name(block, "block/%s"), name(block, "block/%s_planks", "_button")));
        register(blocks.PRESSURE_PLATE, block -> pressurePlate(name(block, "block/%s"), name(block, "block/%s_planks", "_pressure_plate")));
        register(blocks.SIGN, block -> simple(name(block, "block/%s"), particles(name(block, "block/%s_planks", "_sign"))));
        register(blocks.WALL_SIGN, block -> simple(name(block, "block/%s_sign", "_wall_sign"), null)); // same particle-only model as floor sign, avoid double generation
    }*/

    private static void register(Block block, Function<Block, StateGen> genFactory) {
        consumer.accept(block, genFactory.apply(block));
    }

    private static String name(Block block, String nameFormat) {
        Identifier id = Registry.BLOCK.getId(block);

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, id.getPath()));
    }

    private static String name(Block block, String nameFormat, String omitSuffix) {
        Identifier id = Registry.BLOCK.getId(block);

        String path = id.getPath();
        if (path.endsWith(omitSuffix)) {
            path = path.substring(0, path.length() - omitSuffix.length());
        }

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, path));
    }

    private static String name(Block block, String nameFormat, String pattern, String reformat) {
        Identifier id = Registry.BLOCK.getId(block);

        String path = id.getPath();
        path = path.replaceAll(pattern, reformat);

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, path));
    }

    private static String name(Block block) {
        Identifier id = Registry.BLOCK.getId(block);
        return id.toString();
    }

    private static <T> T using(String name, Function<String, T> func) {
        return func.apply(name);
    }


    private BlockStateTable() {
    }
}
