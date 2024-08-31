package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class GGParticleTypes {
    public static final SimpleParticleType GIPPLE = register("gipple", false);

    private static SimpleParticleType register(String name, boolean alwaysShow) {
        return Registry.register(Registries.PARTICLE_TYPE, GipplesGalore.id(name), FabricParticleTypes.simple(alwaysShow));
    }



    public static void init() {}
}
