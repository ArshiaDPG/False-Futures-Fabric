package net.digitalpear.gipples_galore.init.tags;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class GGEntityTypeTags {
    public static final TagKey<EntityType<?>> ANEUPLOIDIAN_TARGET_BLACKLIST = of("aneuploidian_target_blacklist");

    private static TagKey<EntityType<?>> of(String id) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(GipplesGalore.MOD_ID, id));
    }
}
