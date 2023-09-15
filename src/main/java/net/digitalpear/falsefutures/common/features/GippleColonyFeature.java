package net.digitalpear.falsefutures.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.falsefutures.common.blocks.TallJellyrootBlock;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.features.FFConfiguredFeatures;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GippleColonyFeature extends Feature<DefaultFeatureConfig> {
    public GippleColonyFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }


    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {

        BlockPos initialPos = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();

        int radius = random.nextBetween(10, 20);

        if (!world.getBlockState(initialPos).isAir()){
            return false;
        }
//        for (BlockPos pos : BlockPos.iterate(initialPos.add(-radius, -radius/2, -radius), initialPos.add(radius, radius/2, radius))) {
//            if (world.getBlockState(pos).isAir()){
//                return false;
//            }
//        }
        for (BlockPos pos : BlockPos.iterate(initialPos.add(-radius, -radius/2, -radius), initialPos.add(radius, radius/2, radius))) {
            float f = (float)(radius * 3) * 0.333F + 0.5F;
            if (canReplace(world.getBlockState(pos)) && (pos.getSquaredDistance(initialPos) <= (double)(f * f))){
                int blockOutput = random.nextInt(6);

                if (blockOutput == 0){
                    world.setBlockState(pos, Blocks.ICE.getDefaultState(), 3);
                }
                else if (blockOutput == 1){
                    world.setBlockState(pos, FFBlocks.BRINESHALE.getDefaultState(), 3);
                }
                else{
                    world.setBlockState(pos, FFBlocks.GELATITE.getDefaultState(), 3);
                }
            }
        }
        BlockPos newPos = initialPos.up(radius/2);
        for (BlockPos pos : BlockPos.iterate(newPos.add(-radius+2, -radius/2+2, -radius+2), newPos.add(radius-2, radius/2-2, radius-2))) {
            float f = (float) (radius-2 + radius/2-2 + radius-2) * 0.333F + 0.5F;
            if (!world.getBlockState(pos).isIn(BlockTags.FEATURES_CANNOT_REPLACE) && (pos.getSquaredDistance(initialPos) <= (double)(f * f))){
                world.setBlockState(pos, Blocks.CAVE_AIR.getDefaultState(), 3);
            }
        }
        for (BlockPos pos : BlockPos.iterate(initialPos.add(-radius, -radius/2, -radius), initialPos.add(radius, radius/2, radius))) {
            if (random.nextInt(7) == 0){
                if (world.getBlockState(pos).isOf(Blocks.ICE)){
                    for (int i = 1; i < random.nextBetween(2, 10); i++){
                        if (!world.getBlockState(pos.up(i)).isAir()){
                            break;
                        }
                        else{
                            if (random.nextInt(2) == 0){
                                world.setBlockState(pos.up(i), FFBlocks.HIBERNATING_GIPPLE.getDefaultState(), 3);
                            }
                            else{
                                world.setBlockState(pos.up(i), Blocks.ICE.getDefaultState(), 3);
                            }
                        }
                    }
                }
                else if ((world.getBlockState(pos).isOf(FFBlocks.GELATITE) || world.getBlockState(pos).isOf(FFBlocks.BRINESHALE)) && world.getBlockState(pos.up()).isAir()){
                    if (random.nextBoolean()){
                        TallJellyrootBlock.placeAt(world, FFBlocks.TALL_JELLYROOT.getDefaultState(), pos.up(), 3);
                    }
                    else{
                        world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap((registry) ->
                                registry.getEntry(FFConfiguredFeatures.GIPPLE_COLONY_VEGETATION)).ifPresent((reference) ->
                                reference.value().generate(world, context.getGenerator(), random, pos.up()));
                    }
                }
            }
        }
        return true;
    }
    private static boolean canReplace(BlockState state) {
        return state.isIn(FFBlockTags.GIPPLE_COLONY_REPLACEABLE) || state.isAir() || state.getFluidState().isIn(FluidTags.WATER);
    }


}
