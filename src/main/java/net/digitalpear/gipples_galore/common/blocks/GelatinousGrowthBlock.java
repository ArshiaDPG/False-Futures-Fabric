package net.digitalpear.gipples_galore.common.blocks;

import net.digitalpear.gipples_galore.init.tags.GGBlockTags;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class GelatinousGrowthBlock extends PlantBlock implements Waterloggable {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 9.0, 12.0);

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public GelatinousGrowthBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(GGBlockTags.GELATINOUS_GROWTH_SUPPORTING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(WATERLOGGED, ctx.getWorld().getBlockState(ctx.getBlockPos()).isOf(Blocks.WATER));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        super.appendProperties(builder);
    }
}
