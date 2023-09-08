package net.digitalpear.falsefutures.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.features.FFConfiguredFeatures;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
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
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();

        BlockPos offsetPos = initialPos.add(random.nextBetween(-4, 4), 0, random.nextBetween(-4, 4));
        int baseWidth = 12 + random.nextInt(4);
        int baseHeight = 2 + random.nextInt(3);


        //Makes sure the pool does not generate outside the world or in the air
        if (world.getBlockState(initialPos).isSolidBlock(world, initialPos) && isValidSpawn(world, initialPos, baseHeight)){
            makePool(world, random, initialPos, FFBlocks.BRINESHALE.getDefaultState(), baseWidth, baseHeight);
            makePool(world, random, offsetPos, Blocks.WATER.getDefaultState(), baseWidth / 2, baseHeight - 3);
            makePool(world, random, offsetPos.up(baseHeight), CAVE_AIR, baseWidth - 2, baseHeight - 1);
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isValidSpawn(StructureWorldAccess world, BlockPos pos, int baseHeight){
        return pos.down(baseHeight).getY() > world.getBottomY() || world.getTopY() < pos.up(baseHeight).getY();
    }
    private static boolean canReplace(BlockState state) {
        return !state.isIn(FFBlockTags.BRINE_POOL_CANNOT_REPLACE);
    }


    /*
        Makes a sort of sphere out of any block designated
     */
    public static void makePool(StructureWorldAccess serverWorld, Random random, BlockPos pos, BlockState state, int width, int height){
        for(int i = 0; i < random.nextBetween(2, 6); ++i) {
            int x = width + random.nextInt(4);
            int y = height - random.nextInt(2);
            int z = width + random.nextInt(4);
            float f = (float)(x + y + z) * 0.333F + 0.5F;

            for (BlockPos blockPos2 : BlockPos.iterate(pos.add(-x, -y, -z), pos.add(x, y, z))) {
                if (blockPos2.getSquaredDistance(pos) <= (double) (f * f) && canReplace(serverWorld.getBlockState(blockPos2))) {
                    serverWorld.setBlockState(blockPos2, state, 2);


                }
            }
            pos = pos.add(random.nextBetween(-2, 2), random.nextBetween(-2, 2), random.nextBetween(-2, 2));
        }
    }
}
