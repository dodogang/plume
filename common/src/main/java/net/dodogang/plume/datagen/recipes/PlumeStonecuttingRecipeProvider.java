package net.dodogang.plume.datagen.recipes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import net.dodogang.plume.Plume;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Recipe provider designed for generating stonecutter recipes. This provider automatically derives all other possible
 * stonecutting recipes from the registered recipes. This way we don't need to care about these 'recursive recipes',
 * which apparently aren't derived by Minecraft itself.
 */
@SuppressWarnings("unused")
public class PlumeStonecuttingRecipeProvider extends AbstractRecipesProvider {
    private Consumer<RecipeJsonProvider> consumer;

    private final Map<Item, Map<Item, Integer>> recipes = Maps.newHashMap();
    private final Map<Item, List<Pair<Item, Integer>>> compiled = Maps.newHashMap();
    private final Set<String> flushed = Sets.newHashSet();

    public PlumeStonecuttingRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void generate(Consumer<RecipeJsonProvider> consumer) {
        this.consumer = consumer;

        this.addAll();

        this.compile();
        this.flush();
    }

    /**
     * Mixin to add.
     */
    protected void addAll() {}

    private void register(ItemConvertible from, ItemConvertible to) {
        register(from, to, 1);
    }

    private void register(ItemConvertible from, ItemConvertible to, int count) {
        Item fromItem = from.asItem();
        Item toItem = to.asItem();
        recipes.computeIfAbsent(fromItem, k -> Maps.newHashMap())
               .put(toItem, count);
    }

    private void compile() {
        for (Item item : recipes.keySet()) {
            List<Pair<Item, Integer>> results = Lists.newArrayList();
            compile(item, 1, results);
            compiled.put(item, results);
        }
    }

    private void compile(Item from, int countMul, List<Pair<Item, Integer>> out) {
        Map<Item, Integer> to = recipes.get(from);
        for (Map.Entry<Item, Integer> entry : to.entrySet()) {
            Item item = entry.getKey();
            int count = entry.getValue() * countMul;
            out.add(Pair.of(item, count));

            // Recursively walk recipes map
            // If you can stonecut stone to bricks and bricks to bricks slab, you can stonecut stone to bricks slab
            if (recipes.containsKey(item)) {
                compile(item, count, out);
            }
        }
    }

    private void flush() {
        for (Map.Entry<Item, List<Pair<Item, Integer>>> entry : compiled.entrySet()) {
            Item from = entry.getKey();

            List<Pair<Item, Integer>> results = entry.getValue();
            for (Pair<Item, Integer> result : results) {
                Item to = result.getFirst();
                int count = result.getSecond();

                String name = recipeName(from, to);

                // Sometimes multiple ways lead to the same item, meaning a recipe can be registered multiple times.
                // We track all the recipe IDs we flushed in a set so that we only register recipes that we didn't
                // register already. We can assume that when a recipe is registered twice, they are exactly the same and
                // the latter one can be safely omitted...
                if (!flushed.contains(name)) {
                    SingleItemRecipeJsonFactory.create(Ingredient.ofItems(from), to, count)
                                               .create(criterionName(from), hasItem(from))
                                               .offerTo(consumer, new Identifier(name));
                    flushed.add(name);
                }
            }
        }
    }

    private static String criterionName(Item from) {
        Identifier id = Registry.ITEM.getId(from);
        return String.format("has_%s", id.getPath());
    }

    private static String recipeName(Item from, Item to) {
        Identifier fromId = Registry.ITEM.getId(from);
        Identifier toId = Registry.ITEM.getId(to);

        return String.format("%s:stonecutting/%s_to_%s", toId.getNamespace(), fromId.getPath(), toId.getPath());
    }

    @Override
    public String getName() {
        return Plume.MOD_NAME + "StonecuttingRecipes";
    }
}
