package net.digitalpear.falsefutures.common.blocks.jelly;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;

import java.util.ArrayList;
import java.util.List;

public class WarpJellyBlock extends JellyBlock{
    public WarpJellyBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void applySpecialEffects(BlockState initialState, BlockState eatenState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (eatenState != Blocks.AIR.getDefaultState()){
            WorldBorder worldBorder = world.getWorldBorder();

            for(int i = 0; i < 1000; ++i) {
                BlockPos blockPos = pos.add(world.random.nextInt(16) - world.random.nextInt(16), world.random.nextInt(8) - world.random.nextInt(8), world.random.nextInt(16) - world.random.nextInt(16));
                if (world.getBlockState(blockPos).isAir() && worldBorder.contains(blockPos)) {
                    if (world.isClient) {
                        for(int j = 0; j < 128; ++j) {
                            double d = world.random.nextDouble();
                            float x = (world.random.nextFloat() - 0.5F) * 0.2F;
                            float y = (world.random.nextFloat() - 0.5F) * 0.2F;
                            float z = (world.random.nextFloat() - 0.5F) * 0.2F;
                            double e = MathHelper.lerp(d, blockPos.getX(), pos.getX()) + (world.random.nextDouble() - 0.5) + 0.5;
                            double k = MathHelper.lerp(d, blockPos.getY(), pos.getY()) + world.random.nextDouble() - 0.5;
                            double l = MathHelper.lerp(d, blockPos.getZ(), pos.getZ()) + (world.random.nextDouble() - 0.5) + 0.5;
                            world.addParticle(ParticleTypes.PORTAL, e, k, l, x, y, z);
                        }
                    } else {
                        world.setBlockState(blockPos, eatenState, 2);
                        world.removeBlock(pos, false);
                    }

                    return;
                }
            }

        }
    }
}
