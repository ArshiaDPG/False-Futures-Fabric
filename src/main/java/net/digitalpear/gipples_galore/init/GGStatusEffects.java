package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.status.GippleStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class GGStatusEffects {
    public static final RegistryEntry<StatusEffect> GIPPLE = register("gipple", new GippleStatusEffect());

    private static RegistryEntry<StatusEffect> register(String name, StatusEffect effect){
        return Registry.registerReference(Registries.STATUS_EFFECT, GipplesGalore.id(name), effect);
    }
    public static void init() {

    }
}
