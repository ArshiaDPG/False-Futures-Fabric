package net.digitalpear.gipples_galore.init.tags;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class GGBiomeTags {
    public static final TagKey<Biome> NO_GIPPLE_HABITATS = of("no_gipple_habitats");
    public static final TagKey<Biome> EXTRA_GIPPLE_HABITATS = of("extra_gipple_habitats");

    private static TagKey<Biome> of(String id) {
        return TagKey.of(RegistryKeys.BIOME, GipplesGalore.id(id));
    }
}
