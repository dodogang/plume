package net.dodogang.plume.ash.mixin.client.forge;

import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(Item.class)
public interface ItemAccessor {
    /**
     * Necessary because setting the ISTER on forge is done through the block
     * properties which is annoying for an abstraction mod.
     * This might be replaced in the future with a better abstraction.
     */
    // remap = false because this field is part of a forge patch and doesn't get remapped.
    /*@Accessor(value = "ister", remap = false)
    void setIster(Supplier<BuiltinModelItemRenderer> ister);*/
    // TODO
}
