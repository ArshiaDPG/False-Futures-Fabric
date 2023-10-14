package net.digitalpear.gipples_galore.init.data;

import net.digitalpear.gipples_galore.init.GGItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class GGData {

    public static void init(){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(LootTables.ANCIENT_CITY_CHEST)){
                tableBuilder.pool(LootPool.builder()
                        .with(ItemEntry.builder(GGItems.GELATIN).weight(7).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 4.0f))))
                        .with(ItemEntry.builder(GGItems.GIPPLE_BANNER_PATTERN).weight(1))
                        .build());
            }
            else if (id.equals(LootTables.ANCIENT_CITY_ICE_BOX_CHEST)){
                tableBuilder.pool(LootPool.builder()
                                .with(ItemEntry.builder(GGItems.GAPPLE).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0f, 3f)).conditionally(RandomChanceLootCondition.builder(0.75f))))
                        .build());
            }
        });
    }
}
