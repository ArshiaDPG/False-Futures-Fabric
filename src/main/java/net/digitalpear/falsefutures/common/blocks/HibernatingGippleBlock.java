package net.digitalpear.falsefutures.common.blocks;

import net.digitalpear.falsefutures.common.entities.gipple.GippleEntity;
import net.digitalpear.falsefutures.init.FFEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class HibernatingGippleBlock extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public HibernatingGippleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }


    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        this.spawnGipple(world, pos);
    }

    private void spawnGipple(ServerWorld world, BlockPos pos) {
        GippleEntity gipple = FFEntities.GIPPLE.create(world);
        gipple.refreshPositionAndAngles((double) pos.getX() + 0.5D, pos.getY() + 0.25D, (double) pos.getZ() + 0.5D, 0.0F, 0.0f);
        world.spawnEntity(gipple);
    }
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
