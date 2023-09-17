package net.digitalpear.falsefutures.init.features;

import net.digitalpear.falsefutures.FalseFutures;
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
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntry = registryEntryLookup.getOrThrow(FFConfiguredFeatures.GIPPLE_COLONY);

        PlacedFeatures.register(featureRegisterable, GIPPLE_COLONY, registryEntry, RarityFilterPlacementModifier.of(6), HeightRangePlacementModifier.uniform(YOffset.aboveBottom(12), YOffset.fixed(0)), SquarePlacementModifier.of(), BiomePlacementModifier.of());
    }


    public static void init(){
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.FLUID_SPRINGS, GIPPLE_COLONY);
    }
}
