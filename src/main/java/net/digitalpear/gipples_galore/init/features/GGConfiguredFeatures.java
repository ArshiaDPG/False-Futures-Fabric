package net.digitalpear.gipples_galore.init.features;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.blocks.GelatinLayerBlock;
import net.digitalpear.gipples_galore.common.features.GGFeatures;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.tags.GGBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.ArrayList;
import java.util.List;

public class GGConfiguredFeatures {

    public static List<RegistryKey<ConfiguredFeature<?, ?>>> configuredFeatures = new ArrayList<>();
    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        RegistryKey<ConfiguredFeature<?, ?>> placed = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(GipplesGalore.MOD_ID, id));
        configuredFeatures.add(placed);
        return placed;
    }

    public static final RegistryKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY_VEGETATION = of("gipple_colony_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIPPLE_COLONY = of("gipple_colony");




    public static final DataPool.Builder GELATITE_VEGETATION_PROVIDER = DataPool.<BlockState>builder()
            .add(Blocks.AIR.getDefaultState(), 30)
            .add(Blocks.GLOW_LICHEN.getDefaultState()
                    .with(MultifaceGrowthBlock.getProperty(Direction.DOWN), true)
                    .with(MultifaceGrowthBlock.getProperty(Direction.UP), false)
                    .with(MultifaceGrowthBlock.getProperty(Direction.NORTH), false)
                    .with(MultifaceGrowthBlock.getProperty(Direction.SOUTH), false)
                    .with(MultifaceGrowthBlock.getProperty(Direction.EAST), false)
                    .with(MultifaceGrowthBlock.getProperty(Direction.WEST), false), 30);


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        for (int i = 1; i< 9; i++){
            GELATITE_VEGETATION_PROVIDER.add(GGBlocks.GELATIN_LAYER.getDefaultState().with(GelatinLayerBlock.LAYERS, i), 9 - i);
        }

        ConfiguredFeatures.register(featureRegisterable, GIPPLE_COLONY, GGFeatures.GIPPLE_COLONY, new VegetationPatchFeatureConfig(GGBlockTags.GELATINOUS_GROWTH_SUPPORTING, BlockStateProvider.of(GGBlocks.GELATITE), PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(GIPPLE_COLONY_VEGETATION)), VerticalSurfaceType.FLOOR, ConstantIntProvider.create(3), 0.8F, 5, 0.2F, UniformIntProvider.create(6, 9), 0.7F));

        ConfiguredFeatures.register(featureRegisterable, GIPPLE_COLONY_VEGETATION, GGFeatures.GELATITE_VEGETATION, new NetherForestVegetationFeatureConfig(new WeightedBlockStateProvider(GELATITE_VEGETATION_PROVIDER.build()), 8, 5));
    }
}
