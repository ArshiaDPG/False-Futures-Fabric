package net.digitalpear.falsefutures.common.datagens;
import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementManager;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Block;
import net.minecraft.text.Text;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class FFAdvancementGen extends FabricAdvancementProvider {
    public FFAdvancementGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement dummy = Advancement.Builder.create().build(consumer,"husbandry/root");
        var jellies = Advancement.Builder.create()
                .display(
                        FFBlocks.PLAIN_JELLY, // The display icon
                        Text.translatable("advancements.husbandry.jellies.title"), // The title
                        Text.translatable("advancements.husbandry.jellies.description"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                .parent(dummy);
        for(Block jelly : FFBlocks.JELLY.keySet()) {
            jellies.criterion("collected_jelly_" + Registry.BLOCK.getId(jelly).getPath().split("_")[0], InventoryChangedCriterion.Conditions.items(jelly));
        }
        Advancement jelliesAdvancement = jellies.build(consumer, FalseFutures.MOD_ID + ":jellies");
    }
}