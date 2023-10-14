package net.digitalpear.gipples_galore.init.tags;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class GGBannerPatternItemTags {
    public static final TagKey<BannerPattern> GIPPLE_PATTERN_ITEM = of("pattern_item/gipple");


    private static TagKey<BannerPattern> of(String id) {
        return TagKey.of(RegistryKeys.BANNER_PATTERN, new Identifier(GipplesGalore.MOD_ID, id));
    }
}
