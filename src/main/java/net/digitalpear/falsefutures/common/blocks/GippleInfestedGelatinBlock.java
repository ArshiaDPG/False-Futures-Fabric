package net.digitalpear.falsefutures.common.blocks;

import net.digitalpear.falsefutures.common.entities.gipple.GippleEntity;
import net.digitalpear.falsefutures.init.FFEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class GippleInfestedGelatinBlock extends Block {
    public GippleInfestedGelatinBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        this.spawnGipple(world, pos);
    }

    private void spawnGipple(ServerWorld world, BlockPos pos) {
        GippleEntity gipple = FFEntities.GIPPLE.create(world);
        gipple.refreshPositionAndAngles((double) pos.getX() + 0.5D, pos.getY() + 0.25D, (double) pos.getZ() + 0.5D, 0.0F, 0.0F);
        world.spawnEntity(gipple);
        gipple.playSpawnEffects();
    }
}
