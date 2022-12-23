package net.digitalpear.falsefutures.common.features;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;

public class FFFeatures {
    public static final Feature<NetherForestVegetationFeatureConfig> GELASTONE_VEGETATION = register("gelastone_vegetation", new GelastoneVegetationFeature(NetherForestVegetationFeatureConfig.VEGETATION_CODEC));
    public static final Feature<BrinePoolsFeature.Config> BRINE_POOL = register("brine_pool", new BrinePoolsFeature(BrinePoolsFeature.Config.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registry.FEATURE, new Identifier(FalseFutures.MOD_ID, name), feature);
    }
}
