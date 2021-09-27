package net.dodogang.plume.ash.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;

import java.util.function.Supplier;

public final class EntityAttributeRegistry {
    private EntityAttributeRegistry() {}

    @ExpectPlatform
    public static void register(Supplier<EntityType<? extends LivingEntity>> type, Supplier<DefaultAttributeContainer.Builder> attributeMap) {
        throw new AssertionError();
    }
}
