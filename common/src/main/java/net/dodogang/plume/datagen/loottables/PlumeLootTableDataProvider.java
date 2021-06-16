package net.dodogang.plume.datagen.loottables;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.datafixers.util.Pair;
import net.dodogang.plume.Plume;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlumeLootTableDataProvider implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final DataGenerator root;
    private final List<Pair<Supplier<Consumer<BiConsumer<Identifier, LootTable.Builder>>>, LootContextType>> lootTypeGenerators;

    public PlumeLootTableDataProvider(DataGenerator dataGenerator) {
        this.lootTypeGenerators = PlumeLootTableDataProvider.getLootTypeGenerators();
        this.root = dataGenerator;
    }

    /**
     * Mixin to add.
     */
    @NotNull
    protected static List<Pair<Supplier<Consumer<BiConsumer<Identifier, LootTable.Builder>>>, LootContextType>> getLootTypeGenerators() {
        return new ArrayList<>();
    }

    @Override
    public void run(DataCache cache) {
        Path path = this.root.getOutput();
        Map<Identifier, LootTable> map = Maps.newHashMap();

        lootTypeGenerators.forEach(pair -> pair.getFirst().get().accept((id, builder) -> {
            if (map.put(id, builder.type(pair.getSecond()).build()) != null) {
                throw new IllegalStateException("Duplicate loot table " + id);
            }
        }));

        map.forEach((identifierx, lootTable) -> {
            Path output = getOutput(path, identifierx);

            try {
                DataProvider.writeToPath(GSON, cache, LootManager.toJson(lootTable), output);
            } catch (IOException exc) {
                LOGGER.error("Couldn't save loot table {}", output, exc);
            }
        });
    }

    private static Path getOutput(Path rootOutput, Identifier lootTableId) {
        return rootOutput.resolve("data/" + lootTableId.getNamespace() + "/loot_tables/" + lootTableId.getPath() + ".json");
    }

    @Override
    public String getName() {
        return Plume.MOD_NAME + "LootTables";
    }
}
