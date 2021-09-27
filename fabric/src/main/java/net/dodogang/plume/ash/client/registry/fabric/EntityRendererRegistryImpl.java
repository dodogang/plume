package net.dodogang.plume.ash.client.registry.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class EntityRendererRegistryImpl {
    public static <T extends Entity> void register(EntityType<T> type, EntityRendererFactory<T> entityRendererFactory) {
        EntityRendererRegistry.register(type, entityRendererFactory);
    }
}
