package net.digitalpear.gipples_galore.init.artsy_stuff;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public class GGBannerPatterns {


    public static void register(Registerable<BannerPattern> registry, RegistryKey<BannerPattern> key) {
        registry.register(key, new BannerPattern(key.getValue(), "block." + GipplesGalore.MOD_ID + ".banner." + key.getValue().toShortTranslationKey()));
    }
    public static final RegistryKey<BannerPattern> GIPPLE = register("gipple");

    private static RegistryKey<BannerPattern> register(String id) {
        return RegistryKey.of(RegistryKeys.BANNER_PATTERN, GipplesGalore.id(id));
    }
    public static void bootstrap(Registerable<BannerPattern> registry) {
        register(registry, GIPPLE);
    }
}
