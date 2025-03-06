package net.digitalpear.gipples_galore.common.blocks;

import com.mojang.serialization.MapCodec;
import net.digitalpear.gipples_galore.init.GGParticleTypes;
import net.digitalpear.gipples_galore.init.tags.GGBlockTags;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.Vibrations;
import net.minecraft.world.event.listener.VibrationSelector;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class GelatinousGrowthBlock extends PlantBlock implements Waterloggable {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 9.0, 12.0);

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public GelatinousGrowthBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends GelatinousGrowthBlock> getCodec() {
        return createCodec(GelatinousGrowthBlock::new);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getDefaultState() : super.getFluidState(state);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.offset(Direction.DOWN), Direction.UP);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (world.getBlockState(pos.down()).isIn(GGBlockTags.GELATINOUS_GROWTH_SUPPORTING)){
            if (random.nextFloat() < 0.3f){
                for (int i = 0; i < random.nextInt(4); i++){
                    world.addParticle(GGParticleTypes.GIPPLE, (double)pos.getX() + 0.5f, (double)pos.getY() + (random.nextFloat() * 0.7f), (double)pos.getZ() + 0.5f, random.nextFloat() / 2.0F, 5.0E-5, random.nextFloat() / 2.0F);
                }
            }
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (super.getPlacementState(ctx) == null){
            return null;
        }
        return super.getPlacementState(ctx).with(WATERLOGGED, ctx.getWorld().isWater(ctx.getBlockPos()));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        super.appendProperties(builder);
    }
}
