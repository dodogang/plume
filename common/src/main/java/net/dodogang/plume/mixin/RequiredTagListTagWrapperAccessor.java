package net.dodogang.plume.mixin;

import net.minecraft.tag.RequiredTagList;
import net.minecraft.tag.Tag;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RequiredTagList.TagWrapper.class)
public interface RequiredTagListTagWrapperAccessor<T> {
    @Accessor("delegate")
    @Nullable Tag<T> getDelegate();
}
