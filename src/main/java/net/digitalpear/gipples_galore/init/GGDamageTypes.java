package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class GGDamageTypes {
    public static final RegistryKey<DamageType> GIPPLE_EFFECT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(GipplesGalore.MOD_ID, "gipple_effect"));

    public static void bootstrap(Registerable<DamageType> damageTypeRegisterable) {
        damageTypeRegisterable.register(GIPPLE_EFFECT, new DamageType("gippleEffect", DamageScaling.NEVER, 0.0F));
    }


    public static DamageSource gippleEffect(World world) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(GIPPLE_EFFECT));
    }
}
