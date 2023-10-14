package net.digitalpear.gipples_galore.init.features;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.init.tags.GGBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.heightprovider.BiasedToBottomHeightProvider;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

public class GGPlacedFeatures {

    public static List<RegistryKey<PlacedFeature>> placedFeatures = new ArrayList<>();
    public static RegistryKey<PlacedFeature> of(String id) {
        RegistryKey<PlacedFeature> placed = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(GipplesGalore.MOD_ID, id));
        placedFeatures.add(placed);
        return placed;
    }
    public static final RegistryKey<PlacedFeature> GIPPLE_COLONY = of("gipple_colony");
    public static final RegistryKey<PlacedFeature> GIPPLE_COLONY_COMMON = of("gipple_colony_common");



    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> gippleColony = registryEntryLookup.getOrThrow(GGConfiguredFeatures.GIPPLE_COLONY);

        PlacedFeatures.register(featureRegisterable, GIPPLE_COLONY, gippleColony,
                RarityFilterPlacementModifier.of(110),
                CountPlacementModifier.of(12),
                HeightRangePlacementModifier.of(BiasedToBottomHeightProvider.create(YOffset.aboveBottom(10), YOffset.fixed(12), 1)),
                SquarePlacementModifier.of(), BiomePlacementModifier.of());

        PlacedFeatures.register(featureRegisterable, GIPPLE_COLONY_COMMON, gippleColony,
                RarityFilterPlacementModifier.of(60),
                CountPlacementModifier.of(20),
                HeightRangePlacementModifier.of(BiasedToBottomHeightProvider.create(YOffset.aboveBottom(10), YOffset.fixed(12), 1)),
                SquarePlacementModifier.of(), BiomePlacementModifier.of());
    }


    public static void init(){
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.FLUID_SPRINGS, GIPPLE_COLONY);
        BiomeModifications.addFeature(BiomeSelectors.tag(GGBiomeTags.EXTRA_GIPPLE_HABITATS), GenerationStep.Feature.FLUID_SPRINGS, GIPPLE_COLONY_COMMON);
    }
}
