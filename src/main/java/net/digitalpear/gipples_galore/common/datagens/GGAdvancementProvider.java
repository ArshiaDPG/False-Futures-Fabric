package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGStatusEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.EffectsChangedCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Block;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.data.advancement.AdvancementTabGenerator;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GGAdvancementProvider extends FabricAdvancementProvider {


    public GGAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        var jellies = Advancement.Builder.create()
                .display(
                        GGBlocks.PLAIN_JELLY, // The display icon
                        Text.translatable("advancements.husbandry.jellies.title"), // The title
                        Text.translatable("advancements.husbandry.jellies.description"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                .parent(AdvancementTabGenerator.reference("husbandry/root"));
        for(Block jelly : GGBlocks.JELLY.keySet()) {
            jellies.criterion("collected_jelly_" + Registries.BLOCK.getId(jelly).getPath().split("_")[0], InventoryChangedCriterion.Conditions.items(jelly));
        }
        Advancement jelliesAdvancement = jellies.build(consumer, GipplesGalore.MOD_ID + ":husbandry/jellies").value();


        var affordableHousing = Advancement.Builder.create()
                .criterion("has_effects", EffectsChangedCriterion.Conditions.create(new EntityEffectPredicate.Builder().addEffect(GGStatusEffects.GIPPLE).addEffect(StatusEffects.INFESTED).addEffect(StatusEffects.OOZING)))
                .display(
                        PotionContentsComponent.createStack(Items.LINGERING_POTION, Potions.OOZING), // The display icon
                        Text.translatable("advancements.husbandry.affordable_housing.title"), // The title
                        Text.translatable("advancements.husbandry.affordable_housing.description"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                .parent(AdvancementTabGenerator.reference("husbandry/root"));
        Advancement affordableHousingAdvancement = affordableHousing.build(consumer, GipplesGalore.MOD_ID + ":husbandry/affordable_housing").value();
    }
}