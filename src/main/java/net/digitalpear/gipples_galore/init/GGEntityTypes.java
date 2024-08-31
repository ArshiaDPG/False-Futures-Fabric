package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.entities.aneuploidian.AneuploidianEntity;
import net.digitalpear.gipples_galore.common.entities.gipple.GippleEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class GGEntityTypes {

    public static final EntityType<GippleEntity> GIPPLE = Registry.register(
            Registries.ENTITY_TYPE,
            GipplesGalore.gippleID(),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GippleEntity::new).dimensions(EntityDimensions.changing(0.75f, 0.5f).withEyeHeight(0.5f * 0.8f)).build()
    );
    public static final EntityType<AneuploidianEntity> ANEUPLOIDIAN = Registry.register(
            Registries.ENTITY_TYPE,
            GipplesGalore.id("aneuploidian"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AneuploidianEntity::new).dimensions(EntityDimensions.fixed(1.5f, 1.7f).withEyeHeight(1.7f * 0.8f)).build()
    );
    public static void init(){
        FabricDefaultAttributeRegistry.register(GIPPLE, GippleEntity.createGippleAttributes());
        FabricDefaultAttributeRegistry.register(ANEUPLOIDIAN, AneuploidianEntity.createSomethingAttributes());
    }
}
