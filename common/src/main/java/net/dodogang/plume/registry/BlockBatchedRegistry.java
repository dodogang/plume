package net.dodogang.plume.registry;

import net.dodogang.plume.ash.registry.BatchedRegistry;
import net.dodogang.plume.ash.registry.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.NonExtendable
public class BlockBatchedRegistry {
    private final BatchedRegistry<Block> blockRegister;
    private final BatchedRegistry<Item> itemRegister;
    private Item.Settings defaultSettings;

    public BlockBatchedRegistry(String modId) {
        this.blockRegister = BatchedRegistry.create(Registry.BLOCK_KEY, modId);
        this.itemRegister = BatchedRegistry.create(Registry.ITEM_KEY, modId);
        this.defaultSettings = new Item.Settings();
    }

    /**
     * Sets what {@link Item.Settings} should be used by default if not
     * provided when calling the {@link BlockBatchedRegistry#add(String, Block)}
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
     * @param settings the {@link Item.Settings} that will make the block item
     * @return a {@link RegistrySupplier} containing the id of the block
     */
    public <B extends Block>RegistrySupplier<B> add(String name, B block, Item.Settings settings) {
        this.itemRegister.add(name, new BlockItem(block, settings));
        return this.blockRegister.add(name, block);
    }

    /**
     * Adds a block and a {@link BlockItem} with the same name.
     * To construct the block item, it uses the default {@link Item.Settings}
     * See {@link BlockBatchedRegistry#setDefaultItemSettings(Item.Settings)}
     *
     * @param name the name of the block to be combined with the modId to make the id
     * @param block the block to be registered
     * @return a {@link RegistrySupplier} containing the id of the block
     */
    public <B extends Block>RegistrySupplier<B> add(String name, B block) {
        return add(name, block, this.defaultSettings);
    }

    /**
     * Registers all of the blocks and block items in the registry batch.
     * Should only be called once after all objects have been added to the
     * batch.
     */
    public void register() {
        this.blockRegister.register();
        this.itemRegister.register();
    }
}
