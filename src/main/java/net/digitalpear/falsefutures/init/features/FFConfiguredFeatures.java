package net.digitalpear.falsefutures.init.features;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.features.FFFeatures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.List;

public class FFConfiguredFeatures {
    public static final RuleTest DEEP_GELASTONE = new BlockMatchRuleTest(FFBlocks.DEEP_GELASTONE);

    public static final WeightedBlockStateProvider GELASTONE_VEGETATION_PROVIDER = new WeightedBlockStateProvider(DataPool.<BlockState>builder()
            .add(FFBlocks.GELATIN_LAYER.getDefaultState(), 2)
            .add(FFBlocks.JELLYROOT.getDefaultState(), 4)
            .build());
    public static final RegistryEntry<ConfiguredFeature<NetherForestVegetationFeatureConfig, ?>> GELASTONE_VEGETATION = register("gelastone_vegetation", FFFeatures.GELASTONE_VEGETATION, new NetherForestVegetationFeatureConfig(GELASTONE_VEGETATION_PROVIDER, 8, 4));
    public static final RegistryEntry<ConfiguredFeature<NetherForestVegetationFeatureConfig, ?>> GELASTONE_VEGETATION_BONEMEAL = register("gelastone_vegetation_bonemeal", FFFeatures.GELASTONE_VEGETATION, new NetherForestVegetationFeatureConfig(GELASTONE_VEGETATION_PROVIDER, 3, 1));

    public static final RegistryKey<ConfiguredFeature<?, ?>> BRINE_POOL = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(FalseFutures.MOD_ID, "brine_pool"));


    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GELASTONE = register("ore_gelastone", Feature.ORE,
            new OreFeatureConfig(List.of(
                    OreFeatureConfig.createTarget(DEEP_GELASTONE, FFBlocks.GELASTONE.getDefaultState()),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, FFBlocks.DEEP_GELASTONE.getDefaultState()),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, FFBlocks.GELASTONE.getDefaultState())),
                    8));



    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.addCasted(BuiltinRegistries.CONFIGURED_FEATURE, FalseFutures.MOD_ID + ":" + id, new ConfiguredFeature(feature, config));
    }
    public static void init() {
    }
}
