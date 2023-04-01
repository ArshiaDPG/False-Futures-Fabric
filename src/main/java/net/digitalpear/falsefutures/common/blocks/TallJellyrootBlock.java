package net.digitalpear.falsefutures.common.blocks;

import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class TallJellyrootBlock extends TallPlantBlock {
    public TallJellyrootBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(FFBlockTags.JELLYROOT_PLANTABLES);
    }
}
