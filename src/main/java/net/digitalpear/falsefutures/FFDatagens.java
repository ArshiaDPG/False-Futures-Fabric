package net.digitalpear.falsefutures;

import net.digitalpear.falsefutures.common.datagens.*;
import net.digitalpear.falsefutures.common.datagens.tags.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FFDatagens implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        /*
            Tag datagens
         */
        fabricDataGenerator.addProvider(FFItemTagGen::new);
        fabricDataGenerator.addProvider(FFBlockTagGen::new);
        fabricDataGenerator.addProvider(FFEntityTypeTagGen::new);
        fabricDataGenerator.addProvider(FFBannerPatternTagGen::new);
        fabricDataGenerator.addProvider(FFBiomeTagGen::new);

        /*
            Everything else
         */
        fabricDataGenerator.addProvider(FFAdvancementGen::new);
        fabricDataGenerator.addProvider(FFBlockLootTableGen::new);
        fabricDataGenerator.addProvider(FFModelGen::new);
        fabricDataGenerator.addProvider(FFRecipeGen::new);
        fabricDataGenerator.addProvider(FFLanguageProvider::new);
    }
}
