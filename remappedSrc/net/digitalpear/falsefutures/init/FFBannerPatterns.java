package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class FFBannerPatterns {

    public static void createPattern(String name, RegistryKey<BannerPattern> key){
        Registry.register(Registry.BANNER_PATTERN, key, new BannerPattern(name));
    }
    public static final RegistryKey<BannerPattern> GIPPLE = register("gipple");

    private static RegistryKey<BannerPattern> register(String id) {
        return RegistryKey.of(Registry.BANNER_PATTERN_KEY, new Identifier(FalseFutures.MOD_ID, id));
    }

    public static void init(){
        createPattern("gipple", GIPPLE);
    }
}
