package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.GelatinLayerBlock;
import net.digitalpear.falsefutures.common.features.FFFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class FFConfiguredFeatures {



    public static final WeightedBlockStateProvider GELASTONE_VEGETATION_PROVIDER = new WeightedBlockStateProvider(DataPool.<BlockState>builder()
            .add(FFBlocks.GELATIN_LAYER.getDefaultState(), 11)
            .add(FFBlocks.JELLYROOT.getDefaultState(), 3)
            .add(FFBlocks.TALL_JELLYROOT.getDefaultState(), 1)
            .build());
    public static final RegistryEntry<ConfiguredFeature<NetherForestVegetationFeatureConfig, ?>> GELASTONE_VEGETATION = register("gelastone_vegetation", FFFeatures.GELASTONE_VEGETATION, new NetherForestVegetationFeatureConfig(GELASTONE_VEGETATION_PROVIDER, 8, 4));
    public static final RegistryEntry<ConfiguredFeature<NetherForestVegetationFeatureConfig, ?>> GELASTONE_VEGETATION_BONEMEAL = register("gelastone_vegetation_bonemeal", FFFeatures.GELASTONE_VEGETATION, new NetherForestVegetationFeatureConfig(GELASTONE_VEGETATION_PROVIDER, 3, 1));

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.addCasted(BuiltinRegistries.CONFIGURED_FEATURE, FalseFutures.MOD_ID + ":" + id, new ConfiguredFeature(feature, config));
    }
    public static void init() {
    }
}
