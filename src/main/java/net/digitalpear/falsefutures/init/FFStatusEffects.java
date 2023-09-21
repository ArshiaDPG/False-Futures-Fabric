package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.status.GippleStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FFStatusEffects {
    public static final StatusEffect GIPPLE = new GippleStatusEffect();

    public static void init() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(FalseFutures.MOD_ID, "gipple"), GIPPLE);
    }
}
