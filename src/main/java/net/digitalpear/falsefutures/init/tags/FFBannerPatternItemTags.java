package net.digitalpear.falsefutures.init.tags;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFBannerPatternItemTags {
    public static final TagKey<BannerPattern> GIPPLE_PATTERN_ITEM = of("pattern_item/gipple");


    private static TagKey<BannerPattern> of(String id) {
        return TagKey.of(Registry.BANNER_PATTERN_KEY, new Identifier(FalseFutures.MOD_ID, id));
    }
}
