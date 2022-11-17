package net.digitalpear.falsefutures.common.blocks.jelly;

import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WeirdJellyBlock extends JellyBlock{
    public WeirdJellyBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void specialEffects(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 1));
    }
}
