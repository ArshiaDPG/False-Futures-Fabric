package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class GGAdvancementProvider extends FabricAdvancementProvider {
    public GGAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {


        Advancement dummy = Advancement.Builder.create().display(Blocks.HAY_BLOCK, Text.translatable("advancements.husbandry.root.title"), Text.translatable("advancements.husbandry.root.description"), new Identifier("textures/gui/advancements/backgrounds/husbandry.png"), AdvancementFrame.TASK, false, false, false).criterion("consumed_item", (CriterionConditions) ConsumeItemCriterion.Conditions.any()).build(consumer, "husbandry/root");
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
                .parent(dummy);

        for(Block jelly : GGBlocks.JELLY.keySet()) {
            jellies.criterion("collected_jelly_" + Registries.BLOCK.getId(jelly).getPath().split("_")[0], InventoryChangedCriterion.Conditions.items(jelly));
        }
        Advancement jelliesAdvancement = jellies.build(consumer, GipplesGalore.MOD_ID + ":husbandry/jellies");

    }
}