package net.digitalpear.falsefutures.init.features;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.FalseFuturesConfig;
import net.digitalpear.falsefutures.init.tags.FFBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class FFPlacedFeatures {

    public static final RegistryEntry<PlacedFeature> BRINE_POOL = register("brine_pool", FFConfiguredFeatures.BRINE_POOL,
            RarityFilterPlacementModifier.of(9), SquarePlacementModifier.of(),
            HeightRangePlacementModifier.of(UniformHeightProvider.create(YOffset.getBottom(), YOffset.fixed(0))),
            EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.bothOf(BlockPredicate.not(BlockPredicate.IS_AIR),
                    BlockPredicate.insideWorldBounds(new BlockPos(0, -5, 0))), 16),
            SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, -2147483648, -5), BiomePlacementModifier.of());


    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier... modifiers) {
        return PlacedFeatures.register(FalseFutures.MOD_ID + ":" + id, registryEntry, List.of(modifiers));
    }


    public static void init(){
//        BiomeModifications.addFeature(BiomeSelectors.tag(FFBiomeTags.GIPPLE_HABITATS), GenerationStep.Feature.FLUID_SPRINGS, BRINE_POOL.getKey().get());
    }
}
