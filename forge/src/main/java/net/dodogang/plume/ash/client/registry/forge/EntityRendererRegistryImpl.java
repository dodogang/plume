package net.dodogang.plume.ash.client.registry.forge;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.function.Function;

public class EntityRendererRegistryImpl {
    public static <T extends Entity> void register(EntityType<T> type, Function<EntityRenderDispatcher, EntityRenderer<T>> factory) {
        // TODO RenderingRegistry.registerEntityRenderingHandler(type, factory::apply);
    }
}
