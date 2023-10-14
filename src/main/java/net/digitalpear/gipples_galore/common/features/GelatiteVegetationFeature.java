package net.digitalpear.gipples_galore.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.gipples_galore.common.blocks.GelatinLayerBlock;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
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
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        NetherForestVegetationFeatureConfig netherForestVegetationFeatureConfig = context.getConfig();
        Random random = context.getRandom();

        int i = blockPos.getY();
        if (i >= structureWorldAccess.getBottomY() + 1 && i + 1 < structureWorldAccess.getTopY()) {
            int j = 0;

            for(int spreadLoop = 0; spreadLoop < netherForestVegetationFeatureConfig.spreadWidth * netherForestVegetationFeatureConfig.spreadWidth; ++spreadLoop) {
                BlockPos blockPos2 = blockPos.add(random.nextInt(netherForestVegetationFeatureConfig.spreadWidth) - random.nextInt(netherForestVegetationFeatureConfig.spreadWidth), random.nextInt(netherForestVegetationFeatureConfig.spreadHeight) - random.nextInt(netherForestVegetationFeatureConfig.spreadHeight), random.nextInt(netherForestVegetationFeatureConfig.spreadWidth) - random.nextInt(netherForestVegetationFeatureConfig.spreadWidth));
                BlockState blockState2 = netherForestVegetationFeatureConfig.stateProvider.get(random, blockPos2);
                if (structureWorldAccess.isAir(blockPos2) && blockPos2.getY() > structureWorldAccess.getBottomY() && blockState2.canPlaceAt(structureWorldAccess, blockPos2)) {
                    if (blockState2.getBlock() instanceof TallPlantBlock){
                        if (structureWorldAccess.isAir(blockPos2.up())){
                            TallPlantBlock.placeAt(structureWorldAccess, blockState2, blockPos2, 2);
                            ++j;
                        }
                    }
                    else{
                        structureWorldAccess.setBlockState(blockPos2, blockState2, 2);
                        ++j;
                    }

                }
                else if (structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.WATER)
                        && blockPos2.getY() > structureWorldAccess.getBottomY()
                        && blockState2.canPlaceAt(structureWorldAccess, blockPos2)){
                    structureWorldAccess.setBlockState(blockPos2, Blocks.SEAGRASS.getDefaultState(), 2);
                    ++j;
                }
                else if(structureWorldAccess.getBlockState(blockPos2).isOf(GGBlocks.GELATIN_LAYER)
                        && (structureWorldAccess.getBlockState(blockPos2).get(GelatinLayerBlock.LAYERS) < 8)
                        && blockPos2.getY() > structureWorldAccess.getBottomY()
                        && blockState2.canPlaceAt(structureWorldAccess, blockPos2)){
                    structureWorldAccess.setBlockState(blockPos2, GGBlocks.GELATIN_LAYER.getDefaultState()
                            .with(GelatinLayerBlock.LAYERS, structureWorldAccess.getBlockState(blockPos2).get(GelatinLayerBlock.LAYERS) + 1)
                            .with(GelatinLayerBlock.WATERLOGGED, structureWorldAccess.getBlockState(blockPos2).get(GelatinLayerBlock.WATERLOGGED)), 2);
                    ++j;
                }
            }

            return j > 0;
        } else {
            return false;
        }

    }
}
