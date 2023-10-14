package net.digitalpear.falsefutures.common.blocks.jelly;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class SymphonicJellyBlock extends JellyBlock {
    public SymphonicJellyBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void applySpecialEffects(BlockState initialState, BlockState eatenState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            double d = player.getX();
            double e = player.getY();
            double f = player.getZ();

            for(int i = 0; i < 16; ++i) {
                double g = player.getX() + (player.getRandom().nextDouble() - 0.5D) * 16.0D;
                double h = MathHelper.clamp(player.getY() + (double)(player.getRandom().nextInt(16) - 8), world.getBottomY(), world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1);
                double j = player.getZ() + (player.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (FabricLoader.getInstance().isModLoaded("galosphere")){
                    for (BlockPos pos1 : BlockPos.iterate(pos.add(-16, -16, -16), pos.add(16, 16, 16))) {
                        if (world.getBlockState(pos1).isOf(Registries.BLOCK.get(new Identifier("galosphere", "warped_anchor")))
                                && world.getBlockState(pos1.up()).isAir()
                                && world.getBlockState(pos1).getLuminance() > 0) {
                            g = pos1.getX() + 0.5f;
                            h = pos1.getY() + 0.5f;
                            j = pos1.getZ() + 0.5f;
                        }
                    }
                }
                if (player.hasVehicle()) {
                    player.stopRiding();
                }

                Vec3d vec3d = player.getPos();
                if (player.teleport(g, h, j, true)) {
                    world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(player));
                    SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    player.playSound(soundEvent, 1.0F, 1.0F);
                    break;
                }
            }

            player.getItemCooldownManager().set(Item.fromBlock(this), 20);
        }
    }
}
