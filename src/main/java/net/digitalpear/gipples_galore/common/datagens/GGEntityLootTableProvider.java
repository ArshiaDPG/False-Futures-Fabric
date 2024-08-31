package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.init.GGEntityTypes;
import net.digitalpear.gipples_galore.init.GGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class GGEntityLootTableProvider extends SimpleFabricLootTableProvider {

    private final RegistryWrapper.WrapperLookup registryLookup;


    public GGEntityLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ENTITY);
        this.registryLookup = registryLookup.join();
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        lootTableBiConsumer.accept(GGEntityTypes.ANEUPLOIDIAN.getLootTableId(), LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(GGItems.GELATIN)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))).apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 1.0F))).build())
                .pool(LootPool.builder().with(ItemEntry.builder(GGItems.MUSIC_DISC_GIPPLECORE)).conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.create().type(GGEntityTypes.ANEUPLOIDIAN))).build())
        );

        lootTableBiConsumer.accept(GGEntityTypes.GIPPLE.getLootTableId(), LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(GGItems.GELATIN)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))).apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 1.0F))).build())
                .pool(LootPool.builder().with(ItemEntry.builder(GGItems.GAPPLE)).conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.create().type(GGEntityTypes.ANEUPLOIDIAN))).build())
        );
    }
}
