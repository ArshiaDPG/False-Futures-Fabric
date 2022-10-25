package net.digitalpear.falsefutures;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFItems;
import net.digitalpear.falsefutures.init.FFSoundEvents;
import net.fabricmc.api.ModInitializer;

public class FalseFutures implements ModInitializer {

    public static final String MOD_ID = "falsefutures";
    @Override
    public void onInitialize() {
        FFBlocks.init();
        FFItems.init();
        FFEntities.init();
        FFSoundEvents.init();
    }
}
