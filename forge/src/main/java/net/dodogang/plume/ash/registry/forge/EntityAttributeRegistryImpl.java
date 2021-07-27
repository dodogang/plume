package net.dodogang.plume.ash.registry.forge;

import net.dodogang.plume.Plume;
import net.dodogang.plume.ash.forge.ModEventBus;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class EntityAttributeRegistryImpl {
    private static final Map<Supplier<EntityType<? extends LivingEntity>>, Supplier<DefaultAttributeContainer.Builder>> ATTRIBUTES = new ConcurrentHashMap<>();

    public static void register(Supplier<EntityType<? extends LivingEntity>> type, Supplier<DefaultAttributeContainer.Builder> attributeMap) {
        ATTRIBUTES.put(type, attributeMap);
    }

    static {
        ModEventBus.onRegistered(Plume.MOD_ID, bus -> {
            bus.register(EntityAttributeRegistryImpl.class);
        });
    }

    @SubscribeEvent
    public static void event(EntityAttributeCreationEvent event) {
        for (Map.Entry<Supplier<EntityType<? extends LivingEntity>>, Supplier<DefaultAttributeContainer.Builder>> entry : ATTRIBUTES.entrySet()) {
            event.put(entry.getKey().get(), entry.getValue().get().build());
        }
    }
}
