package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class GGPaintings {

    public static final PaintingVariant GIPPLE = of("gipple");

    public static void init() {

    }

    private static PaintingVariant of(String id) {
        return Registry.register(Registries.PAINTING_VARIANT, new Identifier(GipplesGalore.MOD_ID, id), new PaintingVariant(32, 32));
    }
}
