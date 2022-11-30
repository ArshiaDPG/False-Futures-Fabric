package net.digitalpear.falsefutures.init.tags;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFEntityTags {
    public static final TagKey<EntityType<?>> SOMETHING_TARGET_BLACKLIST = of("something_target_blacklist");

    private static TagKey<EntityType<?>> of(String id) {
        return TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(FalseFutures.MOD_ID, id));
    }
}
