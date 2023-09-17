package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.entities.gipple.GippleEntity;
import net.digitalpear.falsefutures.common.entities.aneuploidian.AneuploidianEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FFEntities {

    public static final EntityType<GippleEntity> GIPPLE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(FalseFutures.MOD_ID, "gipple"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GippleEntity::new).dimensions(EntityDimensions.changing(0.75f, 0.5f)).build()
    );
    public static final EntityType<AneuploidianEntity> ANEUPLOIDIAN = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(FalseFutures.MOD_ID, "aneuploidian"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AneuploidianEntity::new).dimensions(EntityDimensions.fixed(1.5f, 1.7f)).build()
    );
    public static void init(){
        FabricDefaultAttributeRegistry.register(GIPPLE, GippleEntity.createGippleAttributes());
        FabricDefaultAttributeRegistry.register(ANEUPLOIDIAN, AneuploidianEntity.createSomethingAttributes());
    }
}
