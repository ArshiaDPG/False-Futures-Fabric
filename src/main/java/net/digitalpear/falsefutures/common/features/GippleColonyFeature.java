package net.digitalpear.falsefutures.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.features.FFConfiguredFeatures;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MiscConfiguredFeatures;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GippleColonyFeature extends Feature<DefaultFeatureConfig> {
    public GippleColonyFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }


    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {

        BlockPos initialPos = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();

        if (world.getBlockState(initialPos).isAir()){
            return false;
        }

        world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap((registry) ->
                registry.getEntry(MiscConfiguredFeatures.SPRING_WATER)).ifPresent((reference) ->
                reference.value().generate(world, context.getGenerator(), random, initialPos));
        BlockPos blockPos = initialPos.up(random.nextBetween(10, 12));
        for(int i = 0; i < 3; ++i) {
            int j = random.nextBetween(3, 5);
            int k = random.nextBetween(3, 5);
            int l = random.nextBetween(3, 5);
            float f = (float)(j + k + l) * 0.333F + 0.5F;

            for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-j, -k, -l), blockPos.add(j, k, l))) {
                if (blockPos2.getSquaredDistance(blockPos) <= (double) (f * f) && canReplace(world.getBlockState(blockPos2))) {
                    world.setBlockState(blockPos2, Blocks.CAVE_AIR.getDefaultState(), 3);
                    if (canReplace(world.getBlockState(blockPos2)) && world.getBlockState(blockPos2).isAir()) {
                        int output = random.nextInt(10);
                        world.setBlockState(blockPos2.down(), FFBlocks.GELATITE.getDefaultState(), 3);

                        if (output == 0) {
                            for (int a = 0; a < random.nextBetween(2, 3); a++) {
                                if (random.nextBoolean()){
                                    world.setBlockState(blockPos2.up(a), Blocks.BLUE_ICE.getDefaultState(), 3);
                                }
                                else{
                                    world.setBlockState(blockPos2.up(a), FFBlocks.GIPPLE_INFESTED_GELATIN.getDefaultState(), 3);
                                }
                            }
                        } else if (output < 5) {
                            world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap((registry) ->
                                    registry.getEntry(FFConfiguredFeatures.GIPPLE_COLONY_VEGETATION_BONEMEAL)).ifPresent((reference) ->
                                    reference.value().generate(world, context.getGenerator(), random, blockPos2));
                        }
                    }
                }
            }

            blockPos = blockPos.add(random.nextBetween(-5, 5), -random.nextBetween(-2, 2), -1 + random.nextBetween(-5, 5));
        }


        return true;
    }
    private static boolean canReplace(BlockState state) {
        return !state.isIn(FFBlockTags.BRINE_POOL_CANNOT_REPLACE);
    }


}
