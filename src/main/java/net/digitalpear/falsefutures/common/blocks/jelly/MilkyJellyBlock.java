package net.digitalpear.falsefutures.common.blocks.jelly;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MilkyJellyBlock extends JellyBlock{
    public MilkyJellyBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void applySpecialEffects(BlockState initialState, BlockState eatenState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            player.clearStatusEffects();
        }
    }
}
