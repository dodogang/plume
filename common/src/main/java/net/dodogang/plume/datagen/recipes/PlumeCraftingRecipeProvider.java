package net.dodogang.plume.datagen.recipes;

import net.dodogang.plume.Plume;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.server.recipe.CookingRecipeJsonFactory;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class PlumeCraftingRecipeProvider extends AbstractRecipesProvider {
    private Consumer<RecipeJsonProvider> consumer;

    public PlumeCraftingRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void generate(Consumer<RecipeJsonProvider> consumer) {
        this.consumer = consumer;
        this.addAll();
    }

    /**
     * Mixin to add.
     */
    protected void addAll() {}

    /*private void woodSet(WoodBlocks blocks, Tag<Item> logsTag) {
        String baseFolder = blocks.getId() + "/";

        planks(baseFolder + "planks", logsTag, blocks.PLANKS);
        wood(baseFolder + "wood", blocks.LOG, blocks.WOOD);
        wood(baseFolder + "stripped_wood", blocks.STRIPPED_LOG, blocks.STRIPPED_WOOD);
        woodenButton(baseFolder + "button", blocks.PLANKS, blocks.BUTTON);
        woodenDoor(baseFolder + "door", blocks.PLANKS, blocks.DOOR);
        woodenFence(baseFolder + "fence", blocks.PLANKS, blocks.FENCE);
        woodenFenceGate(baseFolder + "fence_gate", blocks.PLANKS, blocks.FENCE_GATE);
        woodenPressurePlate(baseFolder + "pressure_plate", blocks.PLANKS, blocks.PRESSURE_PLATE);
        woodenSlab(baseFolder + "slab", blocks.PLANKS, blocks.SLAB);
        woodenStairs(baseFolder + "stairs", blocks.PLANKS, blocks.STAIRS);
        woodenTrapdoor(baseFolder + "trapdoor", blocks.PLANKS, blocks.TRAPDOOR);
        sign(baseFolder + "sign", blocks.PLANKS, blocks.SIGN);
        boat(baseFolder + "boat", blocks.PLANKS, blocks.BOAT_ITEM);
    }*/

    private void generic3x3(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("###")
                               .pattern("###")
                               .pattern("###")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void generic2x2(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("##")
                               .pattern("##")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void generic2x1(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("##")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void generic2x3(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("##")
                               .pattern("##")
                               .pattern("##")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void shapeless(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapelessRecipeJsonFactory.create(to, count)
                                  .input(from)
                                  .criterion("has_ingredient", hasItem(from))
                                  .offerTo(consumer, id);
    }

    private void shapeless(Identifier id,ItemConvertible[] from, ItemConvertible to, int count) {
        ShapelessRecipeJsonFactory factory = ShapelessRecipeJsonFactory.create(to, count)
                                                                       .input(Ingredient.ofItems(from));
        for (ItemConvertible itemC : from) {
            Item item = itemC.asItem();
            String itemId = Registry.ITEM.getId(item)
                                         .getPath();
            factory.criterion("has_" + itemId, hasItem(itemC));
        }

        factory.offerTo(consumer, id);
    }

    private void planks(Identifier id,Tag<Item> from, ItemConvertible to) {
        ShapelessRecipeJsonFactory.create(to, 4)
                                  .input(from)
                                  .group("planks")
                                  .criterion("has_log", hasItems(from))
                                  .offerTo(consumer, id);
    }

    private void planksLogs(Identifier id,Tag<Item> from, ItemConvertible to) {
        ShapelessRecipeJsonFactory.create(to, 4)
                                  .input(from)
                                  .group("planks")
                                  .criterion("has_logs", hasItems(from))
                                  .offerTo(consumer, id);
    }

    private void wood(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to, 3)
                               .input('#', from)
                               .pattern("##")
                               .pattern("##")
                               .group("bark")
                               .criterion("has_log", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void boat(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to)
                               .input('#', from)
                               .pattern("# #")
                               .pattern("###")
                               .group("boat")
                               .criterion("in_water", inFluid(Blocks.WATER))
                               .offerTo(consumer, id);
    }

    private void woodenButton(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapelessRecipeJsonFactory.create(to)
                                  .input(from)
                                  .group("wooden_button")
                                  .criterion("has_planks", hasItem(from))
                                  .offerTo(consumer, id);
    }

    private void woodenDoor(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to, 3)
                               .input('#', from)
                               .pattern("##")
                               .pattern("##")
                               .pattern("##")
                               .group("wooden_door")
                               .criterion("has_planks", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void woodenFence(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to, 3)
                               .input('#', Items.STICK)
                               .input('W', from)
                               .pattern("W#W")
                               .pattern("W#W")
                               .group("wooden_fence")
                               .criterion("has_planks", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void woodenFenceGate(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to)
                               .input('#', Items.STICK)
                               .input('W', from)
                               .pattern("#W#")
                               .pattern("#W#")
                               .group("wooden_fence_gate")
                               .criterion("has_planks", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void woodenPressurePlate(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to)
                               .input('#', from)
                               .pattern("##")
                               .group("wooden_pressure_plate")
                               .criterion("has_planks", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void woodenSlab(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to, 6)
                               .input('#', from)
                               .pattern("###")
                               .group("wooden_slab")
                               .criterion("has_planks", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void woodenStairs(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to, 4)
                               .input('#', from)
                               .pattern("#  ")
                               .pattern("## ")
                               .pattern("###")
                               .group("wooden_stairs")
                               .criterion("has_planks", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void woodenTrapdoor(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to, 2)
                               .input('#', from)
                               .pattern("###")
                               .pattern("###")
                               .group("wooden_trapdoor")
                               .criterion("has_planks", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void sign(Identifier id,ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(from.asItem())
                                     .getPath();
        ShapedRecipeJsonFactory.create(to, 3)
                               .group("sign")
                               .input('#', from)
                               .input('X', Items.STICK)
                               .pattern("###")
                               .pattern("###")
                               .pattern(" X ")
                               .criterion("has_" + string, hasItem(from))
                               .offerTo(consumer, id);
    }

    private void wool(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapelessRecipeJsonFactory.create(to)
                                  .input(from)
                                  .input(Blocks.WHITE_WOOL)
                                  .group("wool")
                                  .criterion("has_white_wool", hasItem(Blocks.WHITE_WOOL))
                                  .offerTo(consumer, id);
    }

    private void carpet(Identifier id,ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(from.asItem())
                                     .getPath();
        ShapedRecipeJsonFactory.create(to, 3)
                               .input('#', from)
                               .pattern("##")
                               .group("carpet")
                               .criterion("has_" + string, hasItem(from))
                               .offerTo(consumer, id);
    }

    private void dyedCarpet(Identifier id,ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(to.asItem())
                                     .getPath();
        String string2 = Registry.ITEM.getId(from.asItem())
                                      .getPath();
        ShapedRecipeJsonFactory.create(to, 8)
                               .input('#', Blocks.WHITE_CARPET)
                               .input('$', from)
                               .pattern("###")
                               .pattern("#$#")
                               .pattern("###")
                               .group("carpet")
                               .criterion("has_white_carpet", hasItem(Blocks.WHITE_CARPET))
                               .criterion("has_" + string2, hasItem(from))
                               .offerTo(consumer, string + "_from_white_carpet");
    }

    private void bed(Identifier id,ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(from.asItem())
                                     .getPath();
        ShapedRecipeJsonFactory.create(to)
                               .input('#', from)
                               .input('X', ItemTags.PLANKS)
                               .pattern("###")
                               .pattern("XXX")
                               .group("bed")
                               .criterion("has_" + string, hasItem(from))
                               .offerTo(consumer, id);
    }

    private void dyedBed(Identifier id,ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(to.asItem())
                                     .getPath();
        ShapelessRecipeJsonFactory.create(to)
                                  .input(Items.WHITE_BED)
                                  .input(from)
                                  .group("dyed_bed")
                                  .criterion("has_bed", hasItem(Items.WHITE_BED))
                                  .offerTo(consumer, string + "_from_white_bed");
    }

    private void banner(Identifier id,ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(from.asItem())
                                     .getPath();
        ShapedRecipeJsonFactory.create(to)
                               .input('#', from)
                               .input('|', Items.STICK)
                               .pattern("###")
                               .pattern("###")
                               .pattern(" | ")
                               .group("banner")
                               .criterion("has_" + string, hasItem(from))
                               .offerTo(consumer, id);
    }

    private void stainedGlass(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to, 8)
                               .input('#', Blocks.GLASS)
                               .input('X', from)
                               .pattern("###")
                               .pattern("#X#")
                               .pattern("###")
                               .group("stained_glass")
                               .criterion("has_glass", hasItem(Blocks.GLASS))
                               .offerTo(consumer, id);
    }

    private void stainedGlassPaneGlass(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to, 16)
                               .input('#', from)
                               .pattern("###")
                               .pattern("###")
                               .group("stained_glass_pane")
                               .criterion("has_glass", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void stainedGlassPaneDye(Identifier id,ItemConvertible from, ItemConvertible to) {
        String string = Registry.ITEM.getId(to.asItem())
                                     .getPath();
        String string2 = Registry.ITEM.getId(from.asItem())
                                      .getPath();
        ShapedRecipeJsonFactory.create(to, 8)
                               .input('#', Blocks.GLASS_PANE)
                               .input('$', from)
                               .pattern("###")
                               .pattern("#$#")
                               .pattern("###")
                               .group("stained_glass_pane")
                               .criterion("has_glass_pane", hasItem(Blocks.GLASS_PANE))
                               .criterion("has_" + string2, hasItem(from))
                               .offerTo(consumer, string + "_from_glass_pane");
    }

    private void stainedTerracotta(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapedRecipeJsonFactory.create(to, 8)
                               .input('#', Blocks.TERRACOTTA)
                               .input('X', from)
                               .pattern("###")
                               .pattern("#X#")
                               .pattern("###")
                               .group("stained_terracotta")
                               .criterion("has_terracotta", hasItem(Blocks.TERRACOTTA))
                               .offerTo(consumer, id);
    }

    private void concretePowder(Identifier id,ItemConvertible from, ItemConvertible to) {
        ShapelessRecipeJsonFactory.create(to, 8)
                                  .input(from)
                                  .input(Blocks.SAND, 4)
                                  .input(Blocks.GRAVEL, 4)
                                  .group("concrete_powder")
                                  .criterion("has_sand", hasItem(Blocks.SAND))
                                  .criterion("has_gravel", hasItem(Blocks.GRAVEL))
                                  .offerTo(consumer, id);
    }

    private void scaffolding(Identifier id,ItemConvertible bamboo, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('/', bamboo)
                               .input('s', Items.STRING)
                               .pattern("/s/")
                               .pattern("/ /")
                               .pattern("/ /")
                               .criterion("has_ingredient", hasItem(bamboo))
                               .offerTo(consumer, id);
    }

    private void generic1x2(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("#")
                               .pattern("#")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void generic1x3(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("#")
                               .pattern("#")
                               .pattern("#")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void generic3x1(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("###")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void generic3x2(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("###")
                               .pattern("###")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void stairs(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("#  ")
                               .pattern("## ")
                               .pattern("###")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void step(Identifier id,ItemConvertible from, ItemConvertible to, int count) {
        ShapedRecipeJsonFactory.create(to, count)
                               .input('#', from)
                               .pattern("#  ")
                               .pattern("## ")
                               .criterion("has_ingredient", hasItem(from))
                               .offerTo(consumer, id);
    }

    private void smelting(Identifier id,ItemConvertible from, ItemConvertible to, double xp) {
        CookingRecipeJsonFactory.createSmelting(Ingredient.ofItems(from), to, (float) xp, 200)
                                .criterion("has_ingredient", hasItem(from))
                                .offerTo(consumer, id);
    }

    private void blasting(Identifier id,ItemConvertible from, ItemConvertible to, double xp) {
        CookingRecipeJsonFactory.createBlasting(Ingredient.ofItems(from), to, (float) xp, 100)
                                .criterion("has_ingredient", hasItem(from))
                                .offerTo(consumer, id);
    }

    @Override
    public String getName() {
        return Plume.MOD_NAME + "Recipes";
    }
}
