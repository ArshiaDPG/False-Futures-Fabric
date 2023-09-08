package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.common.blocks.GippleInfestedBlock;
import net.digitalpear.falsefutures.common.blocks.TallJellyrootBlock;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;

public class FFBlockLootTableGen extends FabricBlockLootTableProvider {
    private static final net.minecraft.loot.condition.LootCondition.Builder WITH_SHEARS = MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().items(Items.SHEARS));

    public FFBlockLootTableGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }


    public LootTable.Builder jellyrootDrops(Block dropWithShears) {
        return dropsWithShears(dropWithShears, (LootPoolEntry.Builder)this.applyExplosionDecay(dropWithShears, ((LeafEntry.Builder) ItemEntry.builder(FFItems.GELATIN).conditionally(RandomChanceLootCondition.builder(0.125F))).apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))));
    }
    public LootTable.Builder tallJellyrootDrops(Block tallGrass, Block grass) {
        LootPoolEntry.Builder<?> builder = ItemEntry.builder(grass).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))).conditionally(WITH_SHEARS).alternatively(((LeafEntry.Builder)addSurvivesExplosionCondition(tallGrass, ItemEntry.builder(FFItems.GELATIN))).conditionally(RandomChanceLootCondition.builder(0.125F)));
        return LootTable.builder().pool(LootPool.builder().with(builder).conditionally(BlockStatePropertyLootCondition.builder(tallGrass).properties(StatePredicate.Builder.create().exactMatch(TallJellyrootBlock.HALF, DoubleBlockHalf.LOWER))).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(tallGrass).state(StatePredicate.Builder.create().exactMatch(TallJellyrootBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).pool(LootPool.builder().with(builder).conditionally(BlockStatePropertyLootCondition.builder(tallGrass).properties(StatePredicate.Builder.create().exactMatch(TallJellyrootBlock.HALF, DoubleBlockHalf.UPPER))).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(tallGrass).state(StatePredicate.Builder.create().exactMatch(TallJellyrootBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }
    public LootTable.Builder slabDrops(Block drop) {
        return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(this.applyExplosionDecay(drop, ItemEntry.builder(drop).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)).conditionally(BlockStatePropertyLootCondition.builder(drop).properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE)))))));
    }

    public void stoneSet(Block stone, Block stairs, Block slab, Block wall, Block button, Block pressurePlate){
        singleDropBlocks(stone, stairs, wall, button, pressurePlate);
        addDrop(slab, slabDrops(slab));
    }


    public void stoneSet(Block stone, Block stairs, Block slab, Block wall){
        addDrop(stone);
        addDrop(stairs);
        addDrop(slab, slabDrops(slab));
        addDrop(wall);
    }
    public void singleDropBlocks(Block... stone){
        Arrays.stream(stone).iterator().forEachRemaining(this::addDrop);
    }

    @Override
    public void generate() {
        stoneSet(FFBlocks.BRINESHALE,
                FFBlocks.BRINESHALE_STAIRS,
                FFBlocks.BRINESHALE_SLAB,
                FFBlocks.BRINESHALE_WALL,
                FFBlocks.BRINESHALE_BUTTON,
                FFBlocks.BRINESHALE_PRESSURE_PLATE);
        drops(FFBlocks.CHISELED_BRINESHALE_BRICKS);

        stoneSet(FFBlocks.GELATITE,
                FFBlocks.GELATITE_STAIRS,
                FFBlocks.GELATITE_SLAB,
                FFBlocks.GELATITE_WALL,
                FFBlocks.GELATITE_BUTTON,
                FFBlocks.GELATITE_PRESSURE_PLATE);

        stoneSet(FFBlocks.GELATITE_BRICKS, FFBlocks.GELATITE_BRICK_STAIRS, FFBlocks.GELATITE_BRICK_SLAB, FFBlocks.GELATITE_BRICK_WALL);
        stoneSet(FFBlocks.BRINESHALE_BRICKS, FFBlocks.BRINESHALE_BRICK_STAIRS, FFBlocks.BRINESHALE_BRICK_SLAB, FFBlocks.BRINESHALE_BRICK_WALL);

        GippleInfestedBlock.REGULAR_TO_INFESTED_BLOCK.forEach((regular, infested) -> drops(infested));

        addDrop(FFBlocks.JELLYROOT, jellyrootDrops(FFBlocks.JELLYROOT));
        addDrop(FFBlocks.TALL_JELLYROOT, tallJellyrootDrops(FFBlocks.TALL_JELLYROOT, FFBlocks.JELLYROOT));
    }
}
