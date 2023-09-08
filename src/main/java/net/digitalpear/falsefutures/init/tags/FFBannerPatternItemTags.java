package net.digitalpear.falsefutures.init.tags;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FFBannerPatternItemTags {
    public static final TagKey<BannerPattern> GIPPLE_PATTERN_ITEM = of("pattern_item/gipple");


    private static TagKey<BannerPattern> of(String id) {
        return TagKey.of(RegistryKeys.BANNER_PATTERN, new Identifier(FalseFutures.MOD_ID, id));
    }
}
