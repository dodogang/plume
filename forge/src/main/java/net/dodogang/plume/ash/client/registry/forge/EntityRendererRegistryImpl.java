package net.dodogang.plume.ash.client.registry.forge;

import net.dodogang.plume.Plume;
import net.dodogang.plume.ash.forge.ModEventBus;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class EntityRendererRegistryImpl {
    private static final Map<Supplier<EntityType<?>>, EntityRendererFactory<?>> RENDERERS = new ConcurrentHashMap<>();

    public static <T extends Entity> void register(Supplier<EntityType<? extends T>> type, EntityRendererFactory<T> factory) {
        RENDERERS.put((Supplier<EntityType<?>>) (Supplier<? extends EntityType<?>>) type, factory);
    }

    static {
        ModEventBus.onRegistered(Plume.MOD_ID, bus -> bus.register(EntityRendererRegistryImpl.class));
    }

    @SubscribeEvent
    public static void event(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<Supplier<EntityType<?>>, EntityRendererFactory<?>> entry : RENDERERS.entrySet()) {
            event.registerEntityRenderer(entry.getKey().get(), (EntityRendererFactory<Entity>) entry.getValue());
        }
    }
}
