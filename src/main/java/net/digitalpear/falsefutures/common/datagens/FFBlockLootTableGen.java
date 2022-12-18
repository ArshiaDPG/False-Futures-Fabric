package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.data.server.EntityLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.BiConsumer;

public class FFBlockLootTableGen extends SimpleFabricLootTableProvider {

    public FFBlockLootTableGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        biConsumer.accept(new Identifier(FalseFutures.MOD_ID, "blocks/gipplepad"), BlockLootTableGenerator.drops(FFItems.GIPPLEPAD));

        stoneSet(biConsumer, FFBlocks.GELASTONE, FFBlocks.GELASTONE_STAIRS, FFBlocks.GELASTONE_SLAB, FFBlocks.GELASTONE_WALL, FFBlocks.GELASTONE_BUTTON, FFBlocks.GELASTONE_PRESSURE_PLATE);
        stoneSet(biConsumer, FFBlocks.GELASTONE_BRICKS, FFBlocks.GELASTONE_BRICK_STAIRS, FFBlocks.GELASTONE_BRICK_SLAB, FFBlocks.GELASTONE_BRICK_WALL, FFBlocks.GELASTONE_BRICK_BUTTON, FFBlocks.GELASTONE_BRICK_PRESSURE_PLATE);
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
}
