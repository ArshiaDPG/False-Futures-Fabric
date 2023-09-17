package net.digitalpear.falsefutures.common.features;

import com.mojang.serialization.Codec;
import net.digitalpear.falsefutures.common.blocks.HibernatingGippleBlock;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.VegetationPatchFeature;
import net.minecraft.world.gen.feature.VegetationPatchFeatureConfig;

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
                set2.add(blockPos);
            }
        }

        var11 = set2.iterator();

        while(var11.hasNext()) {
            blockPos = (BlockPos)var11.next();
            if (random.nextInt(15) == 0){
                generatePillars(world, random, blockPos);
            }
            else{
                world.setBlockState(blockPos, Blocks.WATER.getDefaultState(), 2);
            }
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

            return true;
        } else {
            return false;
        }
    }
    public void generatePillars(StructureWorldAccess world, Random random, BlockPos pos){
        int initialHeight = random.nextBetween(2, 3);
        for (int i = 0; i < initialHeight; i++){
            for (int x = 2; x < 6; x++){
                if (world.getBlockState(pos.up(i)).isAir()){
                    placeBlock(world, random, pos.up(i).offset(Direction.byId(x)));
                }
            }
            if (world.getBlockState(pos.up(i)).isAir()){
                placeBlock(world, random, pos.up(i));
            }
        }
        for (int i = 0; i < initialHeight + random.nextBetween(2, 4); i++){
            if (world.getBlockState(pos.up(i)).isAir()){
                placeBlock(world, random, pos.up(i));
            }
        }
    }
    public void placeBlock(StructureWorldAccess world, Random random, BlockPos pos){
        world.setBlockState(pos, random.nextInt(10) < 8 ? FFBlocks.GELATIN_BLOCK.getDefaultState() : FFBlocks.HIBERNATING_GIPPLE.getDefaultState().with(HibernatingGippleBlock.FACING, Direction.byId(random.nextBetween(2, 5))), 2);
    }


    protected boolean placeGround(StructureWorldAccess world, VegetationPatchFeatureConfig config, Predicate<BlockState> replaceable, Random random, BlockPos.Mutable pos, int depth) {
        for(int i = 0; i < depth; ++i) {
            BlockState gelatite = config.groundState.get(random, pos);
            BlockState floor = world.getBlockState(pos);
            if (!gelatite.isOf(floor.getBlock())) {
                if (!replaceable.test(floor)) {
                    return i != 0;
                }

                world.setBlockState(pos, random.nextInt(10) == 0 ? FFBlocks.AMOEBALITH.getDefaultState() : gelatite, 2);
                pos.move(config.surface.getDirection());
            }
        }

        return true;
    }
}

