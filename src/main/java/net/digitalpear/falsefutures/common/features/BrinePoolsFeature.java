package net.digitalpear.falsefutures.common.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.falsefutures.init.features.FFConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class BrinePoolsFeature  extends Feature<BrinePoolsFeature.Config> {
    private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();

    public BrinePoolsFeature(Codec<BrinePoolsFeature.Config> codec) {
        super(codec);
    }


    public boolean generate(FeatureContext<BrinePoolsFeature.Config> context) {
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        Random random = context.getRandom();
        BrinePoolsFeature.Config config = context.getConfig();
        if (blockPos.getY() <= structureWorldAccess.getBottomY() + 4) {
            return false;
        } else {
            blockPos = blockPos.down(4);
            boolean[] bls = new boolean[2048];
            int i = random.nextInt(4) + 4;

            for(int j = 0; j < i; ++j) {
                double d = random.nextDouble() * 6.0D + 3.0D;
                double e = random.nextDouble() * 4.0D + 2.0D;
                double f = random.nextDouble() * 6.0D + 3.0D;
                double g = random.nextDouble() * (16.0D - d - 2.0D) + 1.0D + d / 2.0D;
                double h = random.nextDouble() * (8.0D - e - 4.0D) + 2.0D + e / 2.0D;
                double k = random.nextDouble() * (16.0D - f - 2.0D) + 1.0D + f / 2.0D;

                for(int l = 1; l < 15; ++l) {
                    for(int m = 1; m < 15; ++m) {
                        for(int n = 1; n < 7; ++n) {
                            double o = ((double)l - g) / (d / 2.0D);
                            double p = ((double)n - h) / (e / 2.0D);
                            double q = ((double)m - k) / (f / 2.0D);
                            double r = o * o + p * p + q * q;
                            if (r < 1.0D) {
                                bls[(l * 16 + m) * 8 + n] = true;
                            }
                        }
                    }
                }
            }

            BlockState lava = config.fluid().getBlockState(random, blockPos);

            int t;
            boolean idk;
            int s;
            int u;
            for(s = 0; s < 16; ++s) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 8; ++u) {
                        idk = !bls[(s * 16 + t) * 8 + u] && (s < 15 && bls[((s + 1) * 16 + t) * 8 + u] || s > 0 && bls[((s - 1) * 16 + t) * 8 + u] || t < 15 && bls[(s * 16 + t + 1) * 8 + u] || t > 0 && bls[(s * 16 + (t - 1)) * 8 + u] || u < 7 && bls[(s * 16 + t) * 8 + u + 1] || u > 0 && bls[(s * 16 + t) * 8 + (u - 1)]);
                        if (idk) {
                            Material material = structureWorldAccess.getBlockState(blockPos.add(s, u, t)).getMaterial();
                            if (u >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (u < 4 && !material.isSolid() && structureWorldAccess.getBlockState(blockPos.add(s, u, t)) != lava) {
                                return false;
                            }
                        }
                    }
                }
            }

            boolean blockState;
            for(s = 0; s < 16; ++s) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 8; ++u) {
                        if (bls[(s * 16 + t) * 8 + u]) {
                            BlockPos blockPos2 = blockPos.add(s, u, t);
                            if (this.canReplace(structureWorldAccess.getBlockState(blockPos2))) {
                                blockState = u >= 4;
                                structureWorldAccess.setBlockState(blockPos2, blockState ? CAVE_AIR : lava, 2);
                                if (blockState) {
                                    structureWorldAccess.createAndScheduleBlockTick(blockPos2, CAVE_AIR.getBlock(), 0);
                                    this.markBlocksAboveForPostProcessing(structureWorldAccess, blockPos2);
                                }
                            }
                        }
                    }
                }
            }

            BlockState stone = config.barrier().getBlockState(random, blockPos);
            if (!stone.isAir()) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 16; ++u) {
                        for(int v = 0; v < 8; ++v) {
                            blockState = !bls[(t * 16 + u) * 8 + v] && (t < 15 && bls[((t + 1) * 16 + u) * 8 + v] || t > 0 && bls[((t - 1) * 16 + u) * 8 + v] || u < 15 && bls[(t * 16 + u + 1) * 8 + v] || u > 0 && bls[(t * 16 + (u - 1)) * 8 + v] || v < 7 && bls[(t * 16 + u) * 8 + v + 1] || v > 0 && bls[(t * 16 + u) * 8 + (v - 1)]);
                            if (blockState && (v < 4 || random.nextInt(2) != 0)) {
                                BlockState blockState3 = structureWorldAccess.getBlockState(blockPos.add(t, v, u));
                                if (blockState3.getMaterial().isSolid() && !blockState3.isIn(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                                    BlockPos blockPos3 = blockPos.add(t, v, u);
                                    structureWorldAccess.setBlockState(blockPos3, stone, 2);
                                    if (random.nextFloat() < 0.6){
                                        ChunkGenerator chunkGenerator = structureWorldAccess.toServerWorld().getChunkManager().getChunkGenerator();
                                        FFConfiguredFeatures.ORE_GELASTONE.value().generate(structureWorldAccess, chunkGenerator, random, blockPos3);
                                    }
                                    if (random.nextFloat() < 0.2) {
                                        ChunkGenerator chunkGenerator = structureWorldAccess.toServerWorld().getChunkManager().getChunkGenerator();
                                        FFConfiguredFeatures.GELASTONE_VEGETATION_BONEMEAL.value().generate(structureWorldAccess, chunkGenerator, random, blockPos3.up());
                                    }
                                    this.markBlocksAboveForPostProcessing(structureWorldAccess, blockPos3);
                                }
                            }
                        }
                    }
                }
            }

            if (lava.getFluidState().isIn(FluidTags.WATER)) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 16; ++u) {
                        idk = true;
                        BlockPos blockPos4 = blockPos.add(t, 4, u);
                        if (structureWorldAccess.getBiome(blockPos4).value().canSetIce(structureWorldAccess, blockPos4, false) && this.canReplace(structureWorldAccess.getBlockState(blockPos4))) {
                            structureWorldAccess.setBlockState(blockPos4, Blocks.ICE.getDefaultState(), 2);
                        }
                    }
                }
            }

            return true;
        }
    }

    private boolean canReplace(BlockState state) {
        return !state.isIn(BlockTags.FEATURES_CANNOT_REPLACE);
    }


    public static record Config(BlockStateProvider fluid, BlockStateProvider barrier) implements FeatureConfig {
        public static final Codec<BrinePoolsFeature.Config> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("fluid").forGetter(BrinePoolsFeature.Config::fluid),
                        BlockStateProvider.TYPE_CODEC.fieldOf("barrier").forGetter(BrinePoolsFeature.Config::barrier)).apply(instance, BrinePoolsFeature.Config::new));

        public Config(BlockStateProvider fluid, BlockStateProvider barrier) {
            this.fluid = fluid;
            this.barrier = barrier;
        }

        public BlockStateProvider fluid() {
            return this.fluid;
        }

        public BlockStateProvider barrier() {
            return this.barrier;
        }
    }
}
