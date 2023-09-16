package net.digitalpear.falsefutures.init.features;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.GelatinLayerBlock;
import net.digitalpear.falsefutures.common.features.FFFeatures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.NetherForestVegetationFeatureConfig;
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
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY = of("gipple_colony");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY_VEGETATION = of("gipple_colony_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY_VEGETATION_BONEMEAL = of("gipple_colony_vegetation_bonemeal");






    public static final WeightedBlockStateProvider GELATITE_VEGETATION_BONEMEAL_PROVIDER = new WeightedBlockStateProvider(DataPool.<BlockState>builder()
            .add(FFBlocks.JELLYROOT.getDefaultState(), 10)
            .build());


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        DataPool.Builder gelatiteVegetationProvider = DataPool.<BlockState>builder()
                .add(FFBlocks.JELLYROOT.getDefaultState(), 30);

        for (int i = 1; i< 9; i++){
            gelatiteVegetationProvider.add(FFBlocks.GELATIN_LAYER.getDefaultState().with(GelatinLayerBlock.LAYERS, i), 9 - i);
        }

        ConfiguredFeatures.register(featureRegisterable, GIPPLE_COLONY, FFFeatures.GIPPLE_COLONY);
        ConfiguredFeatures.register(featureRegisterable, GIPPLE_COLONY_VEGETATION_BONEMEAL, FFFeatures.GELATITE_VEGETATION, new NetherForestVegetationFeatureConfig(GELATITE_VEGETATION_BONEMEAL_PROVIDER, 3, 1));
        ConfiguredFeatures.register(featureRegisterable, GIPPLE_COLONY_VEGETATION, FFFeatures.GELATITE_VEGETATION, new NetherForestVegetationFeatureConfig(new WeightedBlockStateProvider(gelatiteVegetationProvider.build()), 8, 5));
    }
}
