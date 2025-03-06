package net.digitalpear.gipples_galore.common.blocks;

import com.mojang.serialization.MapCodec;
import net.digitalpear.gipples_galore.common.entities.gipple.GippleEntity;
import net.digitalpear.gipples_galore.init.GGEntityTypes;
import net.digitalpear.gipples_galore.init.GGSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class HibernatingGippleBlock extends HorizontalFacingBlock {
    public static final MapCodec<HibernatingGippleBlock> CODEC = createCodec(HibernatingGippleBlock::new);
    public HibernatingGippleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }


    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        this.spawnGipple(world, pos);
    }

    private void spawnGipple(ServerWorld world, BlockPos pos) {
        GippleEntity gipple = GGEntityTypes.GIPPLE.create(world, SpawnReason.MOB_SUMMONED);
        if (gipple != null){
            gipple.refreshPositionAndAngles((double) pos.getX() + 0.5D, pos.getY() + 0.25D, (double) pos.getZ() + 0.5D, 0.0F, 0.0f);
            world.spawnEntity(gipple);
        }
    }
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(200) == 0) {
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), GGSoundEvents.ENTITY_GIPPLE_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
