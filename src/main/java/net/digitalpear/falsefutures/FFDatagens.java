package net.digitalpear.falsefutures;

import net.digitalpear.falsefutures.common.datagens.FFBlockTagGeneration;
import net.digitalpear.falsefutures.common.datagens.FFItemTagGeneration;
import net.digitalpear.falsefutures.common.datagens.FFLanguageProvider;
import net.digitalpear.falsefutures.common.datagens.FFRecipeGeneration;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FFDatagens implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(FFRecipeGeneration::new);
        fabricDataGenerator.addProvider(FFLanguageProvider::new);
        fabricDataGenerator.addProvider(FFItemTagGeneration::new);
        fabricDataGenerator.addProvider(FFBlockTagGeneration::new);
    }
}
