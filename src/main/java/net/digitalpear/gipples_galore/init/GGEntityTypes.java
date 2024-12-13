package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.entities.aneuploidian.AneuploidianEntity;
import net.digitalpear.gipples_galore.common.entities.gipple.GippleEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class GGEntityTypes {

    public static final EntityType<GippleEntity> GIPPLE = register("gipple",EntityType.Builder.create(GippleEntity::new, SpawnGroup.CREATURE).dimensions(0.75f, 0.5f).eyeHeight(0.5f * 0.8f));
    public static final EntityType<AneuploidianEntity> ANEUPLOIDIAN = register("aneuploidian", EntityType.Builder.create(AneuploidianEntity::new, SpawnGroup.MONSTER).dimensions(1.5f, 1.7f).eyeHeight(1.7f * 0.8f));
    
    public static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder){
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, GipplesGalore.id(name));
        return Registry.register(Registries.ENTITY_TYPE, key, builder.build(key));
    }
    public static void init(){
        FabricDefaultAttributeRegistry.register(GIPPLE, GippleEntity.createGippleAttributes());
        FabricDefaultAttributeRegistry.register(ANEUPLOIDIAN, AneuploidianEntity.createSomethingAttributes());
    }
}
