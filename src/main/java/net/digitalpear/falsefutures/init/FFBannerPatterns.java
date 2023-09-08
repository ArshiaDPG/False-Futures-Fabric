package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class FFBannerPatterns {

    public static void createPattern(String name, RegistryKey<BannerPattern> key){
        Registry.register(Registries.BANNER_PATTERN, key, new BannerPattern(name));
    }
    public static final RegistryKey<BannerPattern> GIPPLE = register("gipple");

    private static RegistryKey<BannerPattern> register(String id) {
        return RegistryKey.of(RegistryKeys.BANNER_PATTERN, new Identifier(FalseFutures.MOD_ID, id));
    }

    public static void init(){
        createPattern("gipple", GIPPLE);
    }
}
