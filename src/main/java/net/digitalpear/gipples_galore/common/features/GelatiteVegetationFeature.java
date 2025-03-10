package net.digitalpear.gipples_galore.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.gipples_galore.common.blocks.GelatinLayerBlock;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeagrassBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NetherForestVegetationFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GelatiteVegetationFeature extends Feature<NetherForestVegetationFeatureConfig> {
    public GelatiteVegetationFeature(Codec<NetherForestVegetationFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<NetherForestVegetationFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        NetherForestVegetationFeatureConfig config = context.getConfig();
        Random random = context.getRandom();

        int i = blockPos.getY();
        if (i >= world.getBottomY() + 1 && i + 1 < world.getTopYInclusive()) {
            int j = 0;

            for(int spreadLoop = 0; spreadLoop < config.spreadWidth * config.spreadWidth; ++spreadLoop) {
                BlockPos currentPos = blockPos.add(random.nextInt(config.spreadWidth) - random.nextInt(config.spreadWidth), random.nextInt(config.spreadHeight) - random.nextInt(config.spreadHeight), random.nextInt(config.spreadWidth) - random.nextInt(config.spreadWidth));
                BlockState blockState2 = config.stateProvider.get(random, currentPos);
                if (world.isAir(currentPos) && !blockState2.isIn(BlockTags.UNDERWATER_BONEMEALS) && currentPos.getY() > world.getBottomY() && blockState2.canPlaceAt(world, currentPos)) {
                    if (blockState2.getBlock() instanceof TallPlantBlock){
                        if (world.isAir(currentPos.up())){
                            TallPlantBlock.placeAt(world, blockState2, currentPos, 2);
                            ++j;
                        }
                    }
                    else{
                        world.setBlockState(currentPos, blockState2, 2);
                        ++j;
                    }

                }
                else if (world.isWater(currentPos)
                        && currentPos.getY() > world.getBottomY()
                        && blockState2.canPlaceAt(world, currentPos)){
                    world.setBlockState(currentPos, blockState2.withIfExists(Properties.WATERLOGGED, world.getFluidState(currentPos).isOf(Fluids.WATER)), 2);
                    ++j;
                }
                else if(world.getBlockState(currentPos).isOf(GGBlocks.GELATIN_LAYER)
                        && (world.getBlockState(currentPos).get(GelatinLayerBlock.LAYERS) < 8)
                        && currentPos.getY() > world.getBottomY()
                        && blockState2.canPlaceAt(world, currentPos)){
                    world.setBlockState(currentPos, GGBlocks.GELATIN_LAYER.getDefaultState()
                            .with(GelatinLayerBlock.LAYERS, world.getBlockState(currentPos).get(GelatinLayerBlock.LAYERS) + 1)
                            .with(GelatinLayerBlock.WATERLOGGED, world.getBlockState(currentPos).get(GelatinLayerBlock.WATERLOGGED)), 2);
                    ++j;
                }
            }
            return j > 0;
        } else {
            return false;
        }

    }
}
