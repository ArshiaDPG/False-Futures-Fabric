package net.digitalpear.falsefutures.init.tags;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FFEntityTypeTags {
    public static final TagKey<EntityType<?>> SOMETHING_TARGET_BLACKLIST = of("something_target_blacklist");

    private static TagKey<EntityType<?>> of(String id) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(FalseFutures.MOD_ID, id));
    }
}
