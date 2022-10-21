package net.digitalpear.falsefutures;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.fabricmc.api.ModInitializer;

public class FalseFutures implements ModInitializer {

    public static final String MOD_ID = "falsefutures";
    @Override
    public void onInitialize() {
        FFBlocks.init();
    }
}
