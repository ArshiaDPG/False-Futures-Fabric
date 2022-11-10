package net.digitalpear.falsefutures.common.datagens;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class FFBlockLootTableGen extends SimpleFabricLootTableProvider {
    public FFBlockLootTableGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
//        biConsumer.accept(new Identifier(FalseFutures.MOD_ID, "gily_pad"),
//                BlockLootTableGenerator.drops(FFItems.GIPPLEPAD));
    }
}
