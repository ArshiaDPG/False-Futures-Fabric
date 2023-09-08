package net.digitalpear.falsefutures.init.features;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.features.FFFeatures;
import net.digitalpear.falsefutures.init.tags.FFBiomeTags;
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
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

public class FFPlacedFeatures {

    public static List<RegistryKey<PlacedFeature>> placedFeatures = new ArrayList<>();
    public static RegistryKey<PlacedFeature> of(String id) {
        RegistryKey<PlacedFeature> placed = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(FalseFutures.MOD_ID, id));
        placedFeatures.add(placed);
        return placed;
    }
    public static final RegistryKey<PlacedFeature> GIPPLE_COLONY = of("gipple_colony");



    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry = registryEntryLookup.getOrThrow(VegetationConfiguredFeatures.BAMBOO_NO_PODZOL);

        PlacedFeatures.register(featureRegisterable, GIPPLE_COLONY, registryEntry, RarityFilterPlacementModifier.of(10), HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(0)), SquarePlacementModifier.of(), BiomePlacementModifier.of());

    }


//    public static final RegistryEntry<PlacedFeature> BRINE_POOL = register("brine_pool", FFConfiguredFeatures.BRINE_POOL,
//            RarityFilterPlacementModifier.of(24), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(30)), BiomePlacementModifier.of());
//
//    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
//        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, FalseFutures.MOD_ID + ":" + id, new PlacedFeature(RegistryEntry.upcast(registryEntry), List.copyOf(modifiers)));
//    }
//
//    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier... modifiers) {
//        return register(id, registryEntry, List.of(modifiers));
//    }



    public static void init(){
        BiomeModifications.addFeature(BiomeSelectors.tag(FFBiomeTags.GIPPLE_HABITATS), GenerationStep.Feature.UNDERGROUND_DECORATION, GIPPLE_COLONY);
    }
}
