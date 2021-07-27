package net.dodogang.plume.ash.client.registry.fabric;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.function.Function;

public class EntityRendererRegistryImpl {
    public static <T extends Entity> void register(EntityType<T> type, Function<EntityRenderDispatcher, EntityRenderer<T>> factory) {
        EntityRendererRegistry.INSTANCE.register(type, (manager, context) -> factory.apply(manager));
    }
}
