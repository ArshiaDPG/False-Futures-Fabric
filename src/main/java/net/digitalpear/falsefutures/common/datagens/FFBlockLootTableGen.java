package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.data.server.EntityLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class FFBlockLootTableGen extends SimpleFabricLootTableProvider {
    private static final net.minecraft.loot.condition.LootCondition.Builder WITH_SHEARS = MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().items(Items.SHEARS));
    public FFBlockLootTableGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        biConsumer.accept(new Identifier(FalseFutures.MOD_ID, "blocks/gipplepad"), BlockLootTableGenerator.drops(FFItems.GIPPLEPAD));

        stoneSet(biConsumer, FFBlocks.GELASTONE, FFBlocks.GELASTONE_STAIRS, FFBlocks.GELASTONE_SLAB, FFBlocks.GELASTONE_WALL, FFBlocks.GELASTONE_BUTTON,
                FFBlocks.GELASTONE_PRESSURE_PLATE);
        stoneSet(biConsumer, FFBlocks.GELASTONE_BRICKS, FFBlocks.GELASTONE_BRICK_STAIRS, FFBlocks.GELASTONE_BRICK_SLAB, FFBlocks.GELASTONE_BRICK_WALL,
                FFBlocks.GELASTONE_BRICK_BUTTON, FFBlocks.GELASTONE_BRICK_PRESSURE_PLATE);
        stoneSet(biConsumer, FFBlocks.DEEP_GELASTONE, FFBlocks.DEEP_GELASTONE_STAIRS, FFBlocks.DEEP_GELASTONE_SLAB, FFBlocks.DEEP_GELASTONE_WALL,
                FFBlocks.DEEP_GELASTONE_BUTTON, FFBlocks.DEEP_GELASTONE_PRESSURE_PLATE);
        stoneSet(biConsumer, FFBlocks.DEEP_GELASTONE_BRICKS, FFBlocks.DEEP_GELASTONE_BRICK_STAIRS, FFBlocks.DEEP_GELASTONE_BRICK_SLAB,
                FFBlocks.DEEP_GELASTONE_BRICK_WALL, FFBlocks.DEEP_GELASTONE_BRICK_BUTTON, FFBlocks.DEEP_GELASTONE_BRICK_PRESSURE_PLATE);

        biConsumer.accept(new Identifier(FalseFutures.MOD_ID, "blocks/" + Registry.BLOCK.getId(FFBlocks.JELLYROOT).getPath()), jellyrootDrops(FFBlocks.JELLYROOT));
        biConsumer.accept(new Identifier(FalseFutures.MOD_ID, "blocks/" + Registry.BLOCK.getId(FFBlocks.TALL_JELLYROOT).getPath()), tallJellyrootDrops(FFBlocks.TALL_JELLYROOT, FFBlocks.JELLYROOT));
        biConsumer.accept(new Identifier(FalseFutures.MOD_ID, "blocks/" + Registry.BLOCK.getId(FFBlocks.POTTED_JELLYROOT).getPath()), BlockLootTableGenerator.pottedPlantDrops(FFBlocks.JELLYROOT));
    }
    public static LootTable.Builder jellyrootDrops(Block dropWithShears) {
        return BlockLootTableGenerator.dropsWithShears(dropWithShears, (net.minecraft.loot.entry.LootPoolEntry.Builder)BlockLootTableGenerator.applyExplosionDecay(dropWithShears, ((net.minecraft.loot.entry.LeafEntry.Builder)ItemEntry.builder(FFItems.GELATIN).conditionally(RandomChanceLootCondition.builder(0.125F))).apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))));
    }
    public static LootTable.Builder tallJellyrootDrops(Block tallRoot, Block root) {
        net.minecraft.loot.entry.LootPoolEntry.Builder<?> builder = ItemEntry.builder(root).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))).conditionally(WITH_SHEARS).alternatively(((net.minecraft.loot.entry.LeafEntry.Builder)BlockLootTableGenerator.addSurvivesExplosionCondition(tallRoot, ItemEntry.builder(FFItems.GELATIN))).conditionally(RandomChanceLootCondition.builder(0.125F)));
        return LootTable.builder().pool(LootPool.builder().with(builder).conditionally(BlockStatePropertyLootCondition.builder(tallRoot).properties(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER))).conditionally(LocationCheckLootCondition.builder(net.minecraft.predicate.entity.LocationPredicate.Builder.create().block(net.minecraft.predicate.BlockPredicate.Builder.create().blocks(new Block[]{tallRoot}).state(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).pool(LootPool.builder().with(builder).conditionally(BlockStatePropertyLootCondition.builder(tallRoot).properties(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER))).conditionally(LocationCheckLootCondition.builder(net.minecraft.predicate.entity.LocationPredicate.Builder.create().block(net.minecraft.predicate.BlockPredicate.Builder.create().blocks(new Block[]{tallRoot}).state(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }

    public static void simpleDrop(BiConsumer<Identifier, LootTable.Builder> biConsumer, Block block){
        biConsumer.accept(new Identifier(FalseFutures.MOD_ID, "blocks/" + Registry.BLOCK.getId(block).getPath()), BlockLootTableGenerator.drops(block));
    }

    public static void stoneSet(BiConsumer<Identifier, LootTable.Builder> biConsumer, Block stone, Block stairs, Block slab, Block wall, Block button, Block pressurePlate){
        simpleDrop(biConsumer, stone);
        simpleDrop(biConsumer, stairs);
        biConsumer.accept(new Identifier(FalseFutures.MOD_ID, "blocks/" + Registry.BLOCK.getId(slab).getPath()), BlockLootTableGenerator.slabDrops(slab));
        simpleDrop(biConsumer, wall);
        simpleDrop(biConsumer, button);
        simpleDrop(biConsumer, pressurePlate);
    }
    public static void singleDropBlocks(BiConsumer<Identifier, LootTable.Builder> biConsumer, Block... stone){
        List.of(stone).forEach(block -> {
            simpleDrop(biConsumer, block);
        });
    }
}
