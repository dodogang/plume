package net.dodogang.plume.ash.registry.fabric;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;

import java.util.function.Supplier;

public class EntityAttributeRegistryImpl {
    public static void register(Supplier<EntityType<? extends LivingEntity>> type, Supplier<DefaultAttributeContainer.Builder> attributeMap) {
        FabricDefaultAttributeRegistry.register(type.get(), attributeMap.get());
    }
}
