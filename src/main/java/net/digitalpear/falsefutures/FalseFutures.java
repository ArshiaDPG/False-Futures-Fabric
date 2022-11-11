package net.digitalpear.falsefutures;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFItems;
import net.digitalpear.falsefutures.init.FFSoundEvents;
import net.digitalpear.falsefutures.init.tags.FFBiomeTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.text.Text;

import java.util.concurrent.atomic.AtomicInteger;

public class FalseFutures implements ModInitializer {

    public static final String MOD_ID = "falsefutures";
    @Override
    public void onInitialize() {
        FFBlocks.init();
        FFItems.init();
        FFEntities.init();
        FFSoundEvents.init();

        BiomeModifications.addSpawn(BiomeSelectors.tag(FFBiomeTags.GIPPLE_HABITATS),
                SpawnGroup.UNDERGROUND_WATER_CREATURE, FFEntities.GIPPLE, 2, 1, 3);
    }
}
