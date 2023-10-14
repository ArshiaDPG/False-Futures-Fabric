package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class GGBannerPatterns {

    public static void createPattern(String name, RegistryKey<BannerPattern> key){
        Registry.register(Registries.BANNER_PATTERN, key, new BannerPattern(name));
    }
    public static final RegistryKey<BannerPattern> GIPPLE = register("gipple");

    private static RegistryKey<BannerPattern> register(String id) {
        return RegistryKey.of(RegistryKeys.BANNER_PATTERN, new Identifier(GipplesGalore.MOD_ID, id));
    }

    public static void init(){
        createPattern("gipple", GIPPLE);
    }
}
