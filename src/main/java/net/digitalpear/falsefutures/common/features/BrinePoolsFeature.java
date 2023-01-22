package net.digitalpear.falsefutures.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.features.FFConfiguredFeatures;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BrinePoolsFeature  extends Feature<DefaultFeatureConfig> {
    private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();

    public BrinePoolsFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }


    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {

        BlockPos initialPos = context.getOrigin();
        ServerWorld world = context.getWorld().toServerWorld();

        BlockPos offsetPos = initialPos.add(world.random.nextBetween(-4, 4), 0, world.random.nextBetween(-4, 4));
        int baseWidth = 12 + world.random.nextInt(4);
        int baseHeight = 2 + world.random.nextInt(3);


        //If is in biome where gipples spawn and place of origin is solid block then generate
        if (world.getBlockState(initialPos).isSolidBlock(world, initialPos) && isValidSpawn(world, initialPos, baseHeight)){
            makePool(world, initialPos, FFBlocks.DEEP_GELATITE.getDefaultState(), baseWidth, baseHeight);
            makePool(world, offsetPos, Blocks.WATER.getDefaultState(), baseWidth / 2, baseHeight-3);
            makePool(world, offsetPos.up(baseHeight), CAVE_AIR, baseWidth - 2, baseHeight-1);
            return true;
        }
        return false;
    }

    public static boolean isValidSpawn(World world, BlockPos pos, int baseHeight){
        return pos.down(baseHeight).getY() > world.getBottomY() || world.getTopY() < pos.up(baseHeight).getY();
    }
    private static boolean canReplace(BlockState state) {
        return !state.isIn(FFBlockTags.BRINE_POOL_CANNOT_REPLACE);
    }

    public static void makePool(ServerWorld serverWorld, BlockPos pos, BlockState state, int width, int height){
        for(int i = 0; i < 3; ++i) {
            int x = width + serverWorld.random.nextInt(4);
            int y = height - serverWorld.random.nextInt(2);
            int z = width + serverWorld.random.nextInt(4);
            float f = (float)(x + y + z) * 0.333F + 0.5F;

            for (BlockPos blockPos2 : BlockPos.iterate(pos.add(-x, -y, -z), pos.add(x, y, z))) {
                if (blockPos2.getSquaredDistance(pos) <= (double) (f * f) && canReplace(serverWorld.getBlockState(blockPos2))) {
                    serverWorld.setBlockState(blockPos2, state, 2);

                    //With 10% change spawn gelastone
                    if (serverWorld.random.nextFloat() > 0.9) {
                        FFConfiguredFeatures.ORE_GELASTONE.value().generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(),
                                serverWorld.random, blockPos2);
                    }

                    //With 30% chance if block above is air spawn gelastone vegetation
                    if (serverWorld.random.nextFloat() > 0.7 && serverWorld.getBlockState(blockPos2.up()).isAir()) {
                        FFConfiguredFeatures.GELASTONE_VEGETATION.value().generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(),
                                serverWorld.random, blockPos2);
                    }
                }
            }
            pos = pos.add(serverWorld.random.nextBetween(-2, 2), serverWorld.random.nextBetween(-2, 2), serverWorld.random.nextBetween(-2, 2));
        }
    }
}
