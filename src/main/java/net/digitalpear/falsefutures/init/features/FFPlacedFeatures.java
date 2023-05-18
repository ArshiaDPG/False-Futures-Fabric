package net.digitalpear.falsefutures.init.features;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.tags.FFBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class FFPlacedFeatures {

    public static final RegistryKey<PlacedFeature> BRINE_POOL = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(FalseFutures.MOD_ID, "brine_pool"));



    public static void init(){
        BiomeModifications.addFeature(BiomeSelectors.tag(FFBiomeTags.GIPPLE_HABITATS), GenerationStep.Feature.UNDERGROUND_DECORATION, BRINE_POOL);
    }
}
