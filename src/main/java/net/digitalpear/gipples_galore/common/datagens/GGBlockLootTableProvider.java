package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.common.blocks.GelatinLayerBlock;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

import java.util.Arrays;

public class GGBlockLootTableProvider extends FabricBlockLootTableProvider {
    private static final net.minecraft.loot.condition.LootCondition.Builder WITH_SHEARS = MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().items(Items.SHEARS));

    public GGBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
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

        addDrop(GGBlocks.GELATIN_BLOCK);

        stoneSet(GGBlocks.AMOEBALITH,
                GGBlocks.AMOEBALITH_STAIRS,
                GGBlocks.AMOEBALITH_SLAB,
                GGBlocks.AMOEBALITH_WALL,
                GGBlocks.AMOEBALITH_BUTTON,
                GGBlocks.AMOEBALITH_PRESSURE_PLATE);
        drops(GGBlocks.CHISELED_AMOEBALITH_BRICKS);

        stoneSet(GGBlocks.GELATITE,
                GGBlocks.GELATITE_STAIRS,
                GGBlocks.GELATITE_SLAB,
                GGBlocks.GELATITE_WALL,
                GGBlocks.GELATITE_BUTTON,
                GGBlocks.GELATITE_PRESSURE_PLATE);

        stoneSet(GGBlocks.GELATITE_BRICKS, GGBlocks.GELATITE_BRICK_STAIRS, GGBlocks.GELATITE_BRICK_SLAB, GGBlocks.GELATITE_BRICK_WALL);
        stoneSet(GGBlocks.AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH_BRICK_STAIRS, GGBlocks.AMOEBALITH_BRICK_SLAB, GGBlocks.AMOEBALITH_BRICK_WALL);


        addDrop(GGBlocks.GELATIN_LAYER, gelatinLayerLoot());

        addDrop(GGBlocks.GELATINOUS_GROWTH, gelatinPlantDrops(GGBlocks.GELATINOUS_GROWTH));

        addDrop(GGBlocks.POTTED_GELATINOUS_GROWTH, pottedPlantDrops(GGBlocks.GELATINOUS_GROWTH));
    }
    public LootTable.Builder gelatinPlantDrops(Block dropWithShears) {
        return dropsWithShears(dropWithShears, (LootPoolEntry.Builder)this.applyExplosionDecay(dropWithShears, ((LeafEntry.Builder)ItemEntry.builder(GGItems.GELATIN).conditionally(RandomChanceLootCondition.builder(0.125F))).apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 1))));
    }

    public LootTable.Builder gelatinLayerLoot(){
        LootTable.Builder builder = LootTable.builder();

        for (int i = 0; i <= GelatinLayerBlock.MAX_LAYERS; i++){
            builder.pool(new LootPool.Builder().conditionally(BlockStatePropertyLootCondition.builder(GGBlocks.GELATIN_LAYER).properties(StatePredicate.Builder.create().exactMatch(GelatinLayerBlock.LAYERS, i))).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(i))).with(ItemEntry.builder(GGItems.GELATIN)));

        }
        return builder;
    }
}
