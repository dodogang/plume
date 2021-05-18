package net.dodogang.plume;

import com.google.common.collect.ImmutableList;
import net.dodogang.plume.ash.Environment;
import net.dodogang.plume.ash.registry.FuelRegistry;
import net.dodogang.plume.ash.registry.RegistrySupplier;
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

    private static RegistrySupplier<Block> TEST_BLOCK;
    private static RegistrySupplier<Block> TEST_BEAM_BLOCK;
    private static RegistrySupplier<Block> TEST_CEILING_PLANT_BLOCK;
    private static RegistrySupplier<Block> TEST_TALL_CEILING_PLANT_BLOCK;
    private static RegistrySupplier<Block> TEST_TALLER_PLANT_BLOCK;

    public static void initialize() {
        LOGGER.log(Level.INFO, "Initializing");

        if (runDevTests) {
            LOGGER.log(Level.INFO, "Development environment detected! Running dev initialize.");

            ItemGroup itemGroup = new TabbedItemGroup("plume_test",
                (id) -> {
                    String ns = id.getNamespace();
                    return ImmutableList.of(
                        TabbedItemGroup.createTab(Blocks.DIRT, ns, "unga"),
                        TabbedItemGroup.createTab(Blocks.BLACK_CONCRETE, ns, "bunga")
                    );
                }, () -> new ItemStack(Blocks.STONE)
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
                    TEST_BLOCK.getInitialValue()
            );
        }

        LOGGER.log(Level.INFO, "Initialized");
    }

    public static void setup() {
        if (runDevTests) {
            LOGGER.log(Level.INFO, "Development environment detected! Running dev setup.");

            FuelRegistry.register(80, TEST_BLOCK.get());
            FuelRegistry.register(80, Blocks.DIRT);
            FuelRegistry.register(80, Items.BLUE_DYE);
        }
    }
}
