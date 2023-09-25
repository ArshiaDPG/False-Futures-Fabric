package net.digitalpear.falsefutures.common.blocks;

import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class GelatinousGrowthBlock extends PlantBlock {
    public GelatinousGrowthBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(FFBlockTags.GELATINOUS_GROWTH_SUPPORTING);
    }
}
