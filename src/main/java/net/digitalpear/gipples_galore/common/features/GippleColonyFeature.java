package net.digitalpear.gipples_galore.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.blocks.HibernatingGippleBlock;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.VegetationPatchFeature;
import net.minecraft.world.gen.feature.VegetationPatchFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class GippleColonyFeature extends VegetationPatchFeature {
    public GippleColonyFeature(Codec<VegetationPatchFeatureConfig> codec) {
        super(codec);
    }

    protected Set<BlockPos> placeGroundAndGetPositions(StructureWorldAccess world, VegetationPatchFeatureConfig config, Random random, BlockPos pos, Predicate<BlockState> replaceable, int radiusX, int radiusZ) {
        Set<BlockPos> set = super.placeGroundAndGetPositions(world, config, random, pos, replaceable, radiusX, radiusZ);
        Set<BlockPos> set2 = new HashSet();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Iterator var11 = set.iterator();

        BlockPos blockPos;
        while(var11.hasNext()) {
            blockPos = (BlockPos)var11.next();
            if (!isSolidBlockAroundPos(world, set, blockPos, mutable)) {
//                generateLichen(world, random, pos);
                set2.add(blockPos);
            }
        }

        var11 = set2.iterator();

        while(var11.hasNext()) {
            blockPos = (BlockPos)var11.next();
            world.setBlockState(blockPos, Blocks.WATER.getDefaultState(), 2);

        }


        return set2;
    }
    private static boolean isSolidBlockAroundPos(StructureWorldAccess world, Set<BlockPos> positions, BlockPos pos, BlockPos.Mutable mutablePos) {
        return isSolidBlockSide(world, pos, mutablePos, Direction.NORTH) || isSolidBlockSide(world, pos, mutablePos, Direction.EAST) || isSolidBlockSide(world, pos, mutablePos, Direction.SOUTH) || isSolidBlockSide(world, pos, mutablePos, Direction.WEST) || isSolidBlockSide(world, pos, mutablePos, Direction.DOWN);
    }

    private static boolean isSolidBlockSide(StructureWorldAccess world, BlockPos pos, BlockPos.Mutable mutablePos, Direction direction) {
        mutablePos.set(pos, direction);
        return !world.getBlockState(mutablePos).isSideSolidFullSquare(world, mutablePos, direction.getOpposite());
    }

    protected boolean generateVegetationFeature(StructureWorldAccess world, VegetationPatchFeatureConfig config, ChunkGenerator generator, Random random, BlockPos pos) {
        if (super.generateVegetationFeature(world, config, generator, random, pos.down())) {
            BlockState blockState = world.getBlockState(pos);
            if (blockState.contains(Properties.WATERLOGGED) && !(Boolean)blockState.get(Properties.WATERLOGGED)) {
                world.setBlockState(pos, blockState.with(Properties.WATERLOGGED, true), 2);
            }
            if (random.nextInt(7) == 0){
                generatePillar(world, random, pos);
            }


            return true;
        } else {
            return false;
        }
    }

    public void generatePillar(StructureWorldAccess world, Random random, BlockPos pos){
        int initialHeight = random.nextBetween(2, 3);
        for (int i = -2; i < initialHeight; i++){
            for (int x = 2; x < 6; x++){
                if (!world.getBlockState(pos.up(i)).isOpaque()){
                    placeBlock(world, random, pos.up(i).offset(Direction.byId(x)));
                    if (random.nextBoolean()){
                        world.setBlockState(pos.up(i + 1).offset(Direction.byId(x)), GGBlocks.GELATINOUS_GROWTH.getDefaultState(), 2);
                    }

                }
            }
            if (!world.getBlockState(pos.up(i)).isOpaque()){
                placeBlock(world, random, pos.up(i));
                if (random.nextBoolean()){
                    world.setBlockState(pos.up(i + 1), GGBlocks.GELATINOUS_GROWTH.getDefaultState(), 2);
                }
            }
        }
        int finalHeight = initialHeight + random.nextBetween(2, 4);
        for (int i = initialHeight; i < finalHeight; i++) {
            if (!world.getBlockState(pos.up(i)).isOpaque()) {
                placeBlock(world, random, pos.up(i));
            }
        }
        if (!world.getBlockState(pos.up(finalHeight)).isAir()) {
            world.setBlockState(pos.up(finalHeight), GGBlocks.GELATINOUS_GROWTH.getDefaultState(), 2);
        }

    }
    public void placeBlock(StructureWorldAccess world, Random random, BlockPos pos){
        world.setBlockState(pos, random.nextInt(10) < 8 ? GGBlocks.GELATIN_BLOCK.getDefaultState() : GGBlocks.HIBERNATING_GIPPLE.getDefaultState().with(HibernatingGippleBlock.FACING, Direction.byId(random.nextBetween(2, 5))), 2);
    }


    protected boolean placeGround(StructureWorldAccess world, VegetationPatchFeatureConfig config, Predicate<BlockState> replaceable, Random random, BlockPos.Mutable pos, int depth) {
        for(int i = 0; i < depth; ++i) {
            BlockState gelatite = config.groundState.get(random, pos);
            BlockState floor = world.getBlockState(pos);
            if (!gelatite.isOf(floor.getBlock())) {
                if (!replaceable.test(floor)) {
                    return i != 0;
                }

                Block stone = world.getBlockState(pos).isIn(BlockTags.DEEPSLATE_ORE_REPLACEABLES) ? GGBlocks.AMOEBALITH : GGBlocks.GELATITE;
                world.setBlockState(pos, stone.getDefaultState(), 2);


                pos.move(config.surface.getDirection());
            }
        }

        return true;
    }
}

