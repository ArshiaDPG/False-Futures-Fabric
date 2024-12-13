package net.digitalpear.gipples_galore.common.datagens.providers;

import net.digitalpear.gipples_galore.init.GGPaintingVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class GGPaintingVariantProvider extends FabricDynamicRegistryProvider {
    public GGPaintingVariantProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        add(registries, entries, GGPaintingVariants.GIPPLE);
    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<PaintingVariant> resourceKey) {
        RegistryWrapper.Impl<PaintingVariant> configuredFeatureRegistryLookup = registries.getOrThrow(RegistryKeys.PAINTING_VARIANT);
        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "painting_Variant";
    }
}