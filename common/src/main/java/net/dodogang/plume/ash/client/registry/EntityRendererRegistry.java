package net.dodogang.plume.ash.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class EntityRendererRegistry {
    private EntityRendererRegistry() {}

    @ExpectPlatform
    public static <T extends Entity> void register(Supplier<EntityType<? extends T>> type, EntityRendererFactory<T> provider) {
        throw new AssertionError();
    }
}
