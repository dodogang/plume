package net.dodogang.plume;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.ash.Environment;
import net.dodogang.plume.ash.registry.FuelRegistry;
import net.dodogang.plume.block.BeamBlock;
import net.dodogang.plume.block.CeilingPlantBlock;
import net.dodogang.plume.block.TallCeilingPlantBlock;
import net.dodogang.plume.block.TallerPlantBlock;
import net.dodogang.plume.item.item_group.TabbedItemGroup;
import net.dodogang.plume.registry.BlockRegistryBatch;
import net.dodogang.plume.registry.PointOfInterestTypeAppender;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.poi.PointOfInterestType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"FieldCanBeLocal","unused"})
public final class Plume {
    public static final String MOD_NAME = "Plume";
    public static final String MOD_ID = "plume";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static final boolean runDevTests = Environment.isDevelopmentEnvironment();

    private static Block TEST_BLOCK;
    private static Block TEST_BEAM_BLOCK;
    private static Block TEST_CEILING_PLANT_BLOCK;
    private static Block TEST_TALL_CEILING_PLANT_BLOCK;
    private static Block TEST_TALLER_PLANT_BLOCK;

    public static void initialize() {
        log("Initializing");

        if (runDevTests) {
            log("Development environment detected! Running dev initialize.");

            ItemGroup itemGroup = new TabbedItemGroup(
                "plume_test",
                (group) -> ImmutableList.of(
                    group.createTab("unga", Blocks.DIRT),
                    group.createTab("bunga", Blocks.BLACK_CONCRETE)
                ),
                (group) -> new ItemStack(Blocks.STONE)
            );
            ItemGroup testAdditionalItemGroup = new TabbedItemGroup(
                "plume_test2",
                (group) -> ImmutableList.of(
                    group.createTab("unga", Blocks.GRASS_BLOCK),
                    group.createTab("bunga", Blocks.BLUE_CONCRETE)/*,
                    group.createTab("bunga2", Blocks.YELLOW_BANNER),
                    group.createTab("bunga3", Blocks.YELLOW_BANNER),
                    group.createTab("bunga4", Blocks.YELLOW_BANNER),
                    group.createTab("bunga5", Blocks.YELLOW_BANNER),
                    group.createTab("bunga6", Blocks.YELLOW_BANNER),
                    group.createTab("bunga7", Blocks.YELLOW_BANNER),
                    group.createTab("bunga8", Blocks.YELLOW_BANNER),
                    group.createTab("bunga9", Blocks.YELLOW_BANNER),
                    group.createTab("bunga10", Blocks.YELLOW_BANNER),
                    group.createTab("bunga11", Blocks.YELLOW_BANNER),
                    group.createTab("bunga12", Blocks.YELLOW_BANNER),
                    group.createTab("bunga13", Blocks.YELLOW_BANNER),
                    group.createTab("bunga14", Blocks.YELLOW_BANNER),
                    group.createTab("bunga15", Blocks.YELLOW_BANNER),
                    group.createTab("bunga16", Blocks.YELLOW_BANNER),
                    group.createTab("bunga17", Blocks.YELLOW_BANNER),
                    group.createTab("bunga18", Blocks.YELLOW_BANNER)*/
                ),
                (group) -> new ItemStack(Blocks.STICKY_PISTON)
            );

            BlockRegistryBatch blocks = new BlockRegistryBatch(MOD_ID).setDefaultItemSettings(new Item.Settings().group(itemGroup));

            TEST_BLOCK = blocks.add("test_block", new Block(AbstractBlock.Settings.copy(Blocks.STONE)));
            TEST_BEAM_BLOCK = blocks.add("test_beam_block", new BeamBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
            TEST_CEILING_PLANT_BLOCK = blocks.add("test_ceiling_plant_block", new CeilingPlantBlock(AbstractBlock.Settings.copy(Blocks.GRASS)));
            TEST_TALL_CEILING_PLANT_BLOCK = blocks.add("test_tall_ceiling_plant_block", new TallCeilingPlantBlock(AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));
            TEST_TALLER_PLANT_BLOCK = blocks.add("test_taller_plant_block", new TallerPlantBlock(AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));

            blocks.register();

            PointOfInterestTypeAppender.appendBlocks(
                PointOfInterestType.BUTCHER,
                    TEST_BLOCK
            );
        }

       log("Initialized");
    }

    public static void setup() {
        if (runDevTests) {
            log("Development environment detected! Running dev setup.");

            FuelRegistry.register(80, Plume.TEST_BLOCK);
            FuelRegistry.register(80, Blocks.DIRT);
            FuelRegistry.register(80, Items.BLUE_DYE);
        }
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, "[{}] {}", MOD_NAME, message);
    }
    public static void log(String message) {
        Plume.log(Level.INFO, message);
    }
}
