package net.digitalpear.gipples_galore.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GippleStackBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<Direction> FIRST_DIRECTION = EnumProperty.of("first_facing", Direction.class, Direction.Type.HORIZONTAL);
    public static final EnumProperty<Direction> SECOND_DIRECTION = EnumProperty.of("second_facing", Direction.class, Direction.Type.HORIZONTAL);
    public static final EnumProperty<Direction> THIRD_DIRECTION = EnumProperty.of("third_facing", Direction.class, Direction.Type.HORIZONTAL);
    public static final EnumProperty<Direction> FOURTH_DIRECTION = EnumProperty.of("fourth_facing", Direction.class, Direction.Type.HORIZONTAL);
    public static final IntProperty HEIGHT = IntProperty.of("height", 1, 4);

    public static final List<EnumProperty<Direction>> DIRECTIONS = List.of(
            FIRST_DIRECTION,
            SECOND_DIRECTION,
            THIRD_DIRECTION,
            FOURTH_DIRECTION
    );

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createColumnShape(12, 0, state.get(HEIGHT) * 4);
    }

    public GippleStackBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(WATERLOGGED, false)
                .with(HEIGHT, 1)
                .with(FIRST_DIRECTION, Direction.NORTH)
                .with(SECOND_DIRECTION, Direction.NORTH)
                .with(THIRD_DIRECTION, Direction.NORTH)
                .with(FOURTH_DIRECTION, Direction.NORTH)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, HEIGHT);
        DIRECTIONS.forEach(builder::add);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return super.canPlaceAt(state, world, pos);
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        return super.canReplace(state, context) || (state.isOf(this) && state.get(HEIGHT) < 4 && context.getSide() == Direction.UP);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state == null){
            return null;
        }

        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Boolean isWaterlogged = world.isWater(pos);
        Direction direction = ctx.getHorizontalPlayerFacing().getOpposite();
        BlockState oldState = world.getBlockState(pos);
        int height = 1;
        if (oldState.isOf(this)){
            height = oldState.get(HEIGHT) + 1;
            for (EnumProperty<Direction> property : DIRECTIONS){
                state = state.with(property, oldState.get(property));
            }
        }
        return state.with(WATERLOGGED, isWaterlogged).with(DIRECTIONS.get(height-1), direction).with(HEIGHT, height);
    }
}
