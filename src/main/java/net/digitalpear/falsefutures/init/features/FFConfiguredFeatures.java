package net.digitalpear.falsefutures.init.features;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.GelatinLayerBlock;
import net.digitalpear.falsefutures.common.features.FFFeatures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.ArrayList;
import java.util.List;

public class FFConfiguredFeatures {

    public static List<RegistryKey<ConfiguredFeature<?, ?>>> configuredFeatures = new ArrayList<>();
    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        RegistryKey<ConfiguredFeature<?, ?>> placed = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(FalseFutures.MOD_ID, id));
        configuredFeatures.add(placed);
        return placed;
    }

    public static final RegistryKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY_VEGETATION = of("gipple_colony_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY_VEGETATION_BONEMEAL = of("gipple_colony_vegetation_bonemeal");

    public static final RegistryKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY = of("gipple_colony");





    public static final DataPool.Builder GELATITE_VEGETATION_BONEMEAL_PROVIDER = DataPool.<BlockState>builder()
            .add(FFBlocks.JELLYROOT.getDefaultState(), 30)
            .add(FFBlocks.TALL_JELLYROOT.getDefaultState(), 10);

    public static final DataPool.Builder GELATITE_VEGETATION_BONEMEAL = GELATITE_VEGETATION_BONEMEAL_PROVIDER;


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        for (int i = 1; i< 9; i++){
            GELATITE_VEGETATION_BONEMEAL.add(FFBlocks.GELATIN_LAYER.getDefaultState().with(GelatinLayerBlock.LAYERS, i), 9 - i);
        }

        ConfiguredFeatures.register(featureRegisterable, GIPPLE_COLONY, FFFeatures.GIPPLE_COLONY, new VegetationPatchFeatureConfig(FFBlockTags.GIPPLE_COLONY_REPLACEABLE, BlockStateProvider.of(FFBlocks.GELATITE), PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(GIPPLE_COLONY_VEGETATION)), VerticalSurfaceType.FLOOR, ConstantIntProvider.create(3), 0.8F, 5, 0.1F, UniformIntProvider.create(4, 7), 0.7F));

        ConfiguredFeatures.register(featureRegisterable, GIPPLE_COLONY_VEGETATION_BONEMEAL, FFFeatures.GELATITE_VEGETATION, new NetherForestVegetationFeatureConfig(new WeightedBlockStateProvider(GELATITE_VEGETATION_BONEMEAL_PROVIDER.build()), 3, 1));
        ConfiguredFeatures.register(featureRegisterable, GIPPLE_COLONY_VEGETATION, FFFeatures.GELATITE_VEGETATION, new NetherForestVegetationFeatureConfig(new WeightedBlockStateProvider(GELATITE_VEGETATION_BONEMEAL.build()), 8, 5));
    }
}
