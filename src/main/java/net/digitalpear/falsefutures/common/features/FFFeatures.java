package net.digitalpear.falsefutures.common.features;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class FFFeatures {

    public static final Feature<NetherForestVegetationFeatureConfig> GELATITE_VEGETATION = register("gelatite_vegetation", new GelatiteVegetationFeature(NetherForestVegetationFeatureConfig.VEGETATION_CODEC));
    public static final Feature<VegetationPatchFeatureConfig> GIPPLE_COLONY = register("gipple_colony", new GippleColonyFeature(VegetationPatchFeatureConfig.CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, new Identifier(FalseFutures.MOD_ID, name), feature);
    }


    public static void init() {
    }
}
