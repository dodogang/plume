package net.dodogang.plume.ash.registry;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@ApiStatus.NonExtendable
public abstract class ItemGroupBuilder {
    protected final Identifier id;
    protected Supplier<ItemStack> iconSupplier;
    protected Consumer<List<ItemStack>> stacksToDisplay;

    protected ItemGroupBuilder(Identifier id) {
        this.id = id;
        iconSupplier = () -> ItemStack.EMPTY;
    }

    @ExpectPlatform
    public static ItemGroupBuilder create(Identifier id) {
        throw new AssertionError();
    }

    public ItemGroupBuilder icon(Supplier<ItemStack> iconSupplier) {
        this.iconSupplier = iconSupplier;
        return this;
    }

    public ItemGroupBuilder items(Consumer<List<ItemStack>> stacksToDisplay) {
        this.stacksToDisplay = stacksToDisplay;
        return this;
    }

    public static ItemGroup build(Identifier id, Supplier<ItemStack> iconSupplier) {
        return create(id).icon(iconSupplier).build();
    }

    public abstract ItemGroup build();
}
