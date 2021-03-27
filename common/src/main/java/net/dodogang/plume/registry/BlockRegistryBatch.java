package net.dodogang.plume.registry;

import net.dodogang.plume.ash.registry.RegistryBatch;
import net.dodogang.plume.ash.registry.RegistrySupplier;
import net.dodogang.plume.block.PlumeStairsBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

@ApiStatus.NonExtendable
public class BlockRegistryBatch {
    protected final RegistryBatch<Block> blockRegistry;
    protected final RegistryBatch<Item> itemRegistry;
    protected Item.Settings defaultSettings;

    public BlockRegistryBatch(String modId) {
        this.blockRegistry = RegistryBatch.create(Registry.BLOCK_KEY, modId);
        this.itemRegistry = RegistryBatch.create(Registry.ITEM_KEY, modId);
        this.defaultSettings = new Item.Settings();
    }

    /**
     * Sets what {@link Item.Settings} should be used by default if not
     * provided when calling the {@link BlockRegistryBatch#add(String, Block)}
     * method.
     *
     * The default defaults to an unmodified new Item.Settings();
     *
     * @param settings the new default item settings
     */
    public void setDefaultItemSettings(Item.Settings settings) {
        this.defaultSettings = settings;
    }

    /**
     * Adds a block and a {@link BlockItem} with the same name.
     *
     * @param name the name of the block to be combined with the modId to make the id
     * @param block the block to be registered
     * @param settings the {@link Item.Settings} that will make the block item.
     * @return a {@link RegistrySupplier} containing the id of the block
     */
    public <B extends Block>RegistrySupplier<B> add(String name, B block, @Nullable Item.Settings settings) {
        if (settings != null) {
            this.itemRegistry.add(name, new BlockItem(block, settings));
        }
        return this.blockRegistry.add(name, block);
    }

    /**
     * Adds a block and a {@link BlockItem} with the same name.
     * To construct the block item, it uses the default {@link Item.Settings}.
     * See {@link BlockRegistryBatch#setDefaultItemSettings(Item.Settings)}.
     *
     * @param name the name of the block to be combined with the modId to make the id
     * @param block the block to be registered
     * @return a {@link RegistrySupplier} containing the id of the block
     */
    public <B extends Block>RegistrySupplier<B> add(String name, B block) {
        return add(name, block, this.defaultSettings);
    }

    /**
     * Adds a block and a {@link BlockItem} with the same name.
     * To construct the block item, it uses the default {@link Item.Settings}.
     * See {@link BlockRegistryBatch#setDefaultItemSettings(Item.Settings)}.
     *
     * @param name the name of the block to be combined with the modId to make the id
     * @param blockFunction a function that constructs a block from a {@link AbstractBlock.Settings}
     * @param toCopy an {@link AbstractBlock} that will have its block settings copied.
     * @return a {@link RegistrySupplier} containing the id of the block
     */
    public <B extends Block> RegistrySupplier<B> addCopy(
            String name,
            Function<AbstractBlock.Settings, B> blockFunction,
            AbstractBlock toCopy
    ) {
        return this.add(name, blockFunction.apply(AbstractBlock.Settings.copy(toCopy)));
    }

    /**
     * Adds a {@link PlumeStairsBlock} and a {@link BlockItem} with the same name.
     * To construct the block item, it uses the default {@link Item.Settings}.
     * See {@link BlockRegistryBatch#setDefaultItemSettings(Item.Settings)}.
     *
     * @param name the name of the block to be combined with the modId to make the id
     * @param toCopy an {@link AbstractBlock} that will have its block settings copied.
     * @return a {@link RegistrySupplier} containing the id of the block
     */
    public RegistrySupplier<PlumeStairsBlock> addStairCopy(String name, Block toCopy) {
        return this.add(name, new PlumeStairsBlock(toCopy.getDefaultState(), AbstractBlock.Settings.copy(toCopy)));
    }

    /**
     * Registers all of the blocks and block items in the registry batch.
     * Should only be called once after all objects have been added to the
     * batch.
     */
    public void register() {
        this.blockRegistry.register();
        this.itemRegistry.register();
    }
}
