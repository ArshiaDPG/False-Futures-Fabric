package net.digitalpear.gipples_galore.common.datagens.providers;

import net.digitalpear.gipples_galore.init.GGDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class GGDamageTypeProvider extends FabricDynamicRegistryProvider {
    public GGDamageTypeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        add(registries, entries, GGDamageTypes.GIPPLE_EFFECT);
    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<DamageType> resourceKey) {
        RegistryWrapper.Impl<DamageType> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE);
        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "damage_type";
    }
}