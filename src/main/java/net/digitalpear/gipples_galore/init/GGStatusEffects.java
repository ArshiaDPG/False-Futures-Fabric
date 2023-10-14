package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.status.GippleStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GGStatusEffects {
    public static final StatusEffect GIPPLE = new GippleStatusEffect();

    public static void init() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(GipplesGalore.MOD_ID, "gipple"), GIPPLE);
    }
}
