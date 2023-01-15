package net.digitalpear.falsefutures.common.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.features.FFConfiguredFeatures;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

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

        makePool(world, initialPos, FFBlocks.DEEP_GELASTONE.getDefaultState(), baseWidth, baseHeight);
        makePool(world, offsetPos,
                Blocks.WATER.getDefaultState(), baseWidth / 2, baseHeight-3);
        makePool(world, offsetPos.up(baseHeight), CAVE_AIR, baseWidth - 2, baseHeight-1);


        return true;
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

                    if (serverWorld.random.nextFloat() > 0.9) {
                        FFConfiguredFeatures.ORE_GELASTONE.value().generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(),
                                serverWorld.random, blockPos2);
                    }
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
