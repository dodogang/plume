package net.dodogang.plume.ash.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class EntityRendererRegistry {
    private EntityRendererRegistry() {}

    @ExpectPlatform
    public static <T extends Entity> void register(EntityType<T> type, Function<EntityRenderDispatcher, EntityRenderer<T>> factory) {
        throw new AssertionError();
    }
}
