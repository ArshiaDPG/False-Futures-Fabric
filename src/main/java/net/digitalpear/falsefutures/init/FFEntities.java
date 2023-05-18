package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.entities.gipple.GippleEntity;
import net.digitalpear.falsefutures.common.entities.something.SomethingEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFEntities {

    public static final EntityType<GippleEntity> GIPPLE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(FalseFutures.MOD_ID, "gipple"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GippleEntity::new).dimensions(EntityDimensions.changing(0.75f, 0.5f)).build()
    );
    public static final EntityType<SomethingEntity> SOMETHING = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(FalseFutures.MOD_ID, "something"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SomethingEntity::new).dimensions(EntityDimensions.fixed(1.5f, 1.7f)).build()
    );
    public static void init(){
        FabricDefaultAttributeRegistry.register(GIPPLE, GippleEntity.createGippleAttributes());
        FabricDefaultAttributeRegistry.register(SOMETHING, SomethingEntity.createSomethingAttributes());
    }
}
