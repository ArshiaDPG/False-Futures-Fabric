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
    public static final RuleTest BRINESHALE = new BlockMatchRuleTest(FFBlocks.BRINESHALE);

    public static final WeightedBlockStateProvider GELATITE_VEGETATION_PROVIDER = new WeightedBlockStateProvider(DataPool.<BlockState>builder()
            .add(FFBlocks.GELATIN_LAYER.getDefaultState(), 2)
            .add(FFBlocks.JELLYROOT.getDefaultState(), 4)
            .build());
    public static final RegistryEntry<ConfiguredFeature<NetherForestVegetationFeatureConfig, ?>> GELATITE_VEGETATION = register("gelatite_vegetation", FFFeatures.GELATITE_VEGETATION, new NetherForestVegetationFeatureConfig(GELATITE_VEGETATION_PROVIDER, 8, 4));
    public static final RegistryEntry<ConfiguredFeature<NetherForestVegetationFeatureConfig, ?>> GELATITE_VEGETATION_BONEMEAL = register("gelatite_vegetation_bonemeal", FFFeatures.GELATITE_VEGETATION, new NetherForestVegetationFeatureConfig(GELATITE_VEGETATION_PROVIDER, 3, 1));

    public static final RegistryKey<ConfiguredFeature<?, ?>> BRINE_POOL = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(FalseFutures.MOD_ID, "brine_pool"));


    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GELATITE = register("ore_gelatite", Feature.ORE,
            new OreFeatureConfig(List.of(
                    OreFeatureConfig.createTarget(BRINESHALE, FFBlocks.GELATITE.getDefaultState()),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, FFBlocks.BRINESHALE.getDefaultState()),
                    OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, FFBlocks.GELATITE.getDefaultState())),
                    8));



    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.addCasted(BuiltinRegistries.CONFIGURED_FEATURE, FalseFutures.MOD_ID + ":" + id, new ConfiguredFeature(feature, config));
    }
    public static void init() {
    }
}
