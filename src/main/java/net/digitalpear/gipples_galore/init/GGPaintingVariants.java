package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class GGPaintingVariants {

    public static final RegistryKey<PaintingVariant> GIPPLE = of("gipple");
    private static void register(Registerable<PaintingVariant> registry, RegistryKey<PaintingVariant> key, int width, int height) {
        registry.register(key, new PaintingVariant(width, height, key.getValue()));
    }
    public static void bootstrap(Registerable<PaintingVariant> registry) {
        register(registry, GIPPLE, 2, 2);
    }

    private static RegistryKey<PaintingVariant> of(String id) {
        return RegistryKey.of(RegistryKeys.PAINTING_VARIANT, GipplesGalore.id(id));
    }
}
