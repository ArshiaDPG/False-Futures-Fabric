package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.init.GGEntities;
import net.digitalpear.gipples_galore.init.GGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class GGEntityLootTableProvider extends SimpleFabricLootTableProvider {

    public GGEntityLootTableProvider(FabricDataOutput output) {
        super(output, LootContextTypes.ENTITY);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
        identifierBuilderBiConsumer.accept(Registries.ENTITY_TYPE.getId(GGEntities.ANEUPLOIDIAN).withPrefixedPath("entities/"), LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(GGItems.GELATIN)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))).build())
                .pool(LootPool.builder().with(ItemEntry.builder(GGItems.MUSIC_DISC_GIPPLECORE)).conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.create().type(GGEntities.ANEUPLOIDIAN))).build()));

        identifierBuilderBiConsumer.accept(Registries.ENTITY_TYPE.getId(GGEntities.GIPPLE).withPrefixedPath("entities/"), LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(GGItems.GELATIN)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))).build())
                .pool(LootPool.builder().with(ItemEntry.builder(GGItems.GAPPLE)).conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.create().type(GGEntities.ANEUPLOIDIAN))).build()));
    }
}
