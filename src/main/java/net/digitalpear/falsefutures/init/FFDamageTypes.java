package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class FFDamageTypes {
    public static final RegistryKey<DamageType> GIPPLE_EFFECT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(FalseFutures.MOD_ID, "gipple_effect"));

    public static DamageSource gippleEffect(World world) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(GIPPLE_EFFECT));
    }
}
