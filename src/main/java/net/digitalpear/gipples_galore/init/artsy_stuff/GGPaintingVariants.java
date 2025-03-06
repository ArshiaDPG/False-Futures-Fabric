package net.digitalpear.gipples_galore.init.artsy_stuff;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Optional;

public class GGPaintingVariants {

    public static final RegistryKey<PaintingVariant> GIPPLE = of("gipple");
    private static void register(Registerable<PaintingVariant> registry, RegistryKey<PaintingVariant> key, int width, int height) {
        register(registry, key, width, height, true);
    }

    private static void register(Registerable<PaintingVariant> registry, RegistryKey<PaintingVariant> key, int width, int height, boolean hasAuthor) {
        registry.register(key, new PaintingVariant(width, height, key.getValue(), Optional.of(Text.translatable(key.getValue().toTranslationKey("painting", "title")).formatted(Formatting.YELLOW)), hasAuthor ? Optional.of(Text.translatable(key.getValue().toTranslationKey("painting", "author")).formatted(Formatting.GRAY)) : Optional.empty()));
    }
    public static void bootstrap(Registerable<PaintingVariant> registry) {
        register(registry, GIPPLE, 2, 2);
    }

    private static RegistryKey<PaintingVariant> of(String id) {
        return RegistryKey.of(RegistryKeys.PAINTING_VARIANT, GipplesGalore.id(id));
    }
}
