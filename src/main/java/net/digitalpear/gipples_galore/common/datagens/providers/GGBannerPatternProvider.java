package net.digitalpear.gipples_galore.common.datagens.providers;

import net.digitalpear.gipples_galore.init.artsy_stuff.GGBannerPatterns;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class GGBannerPatternProvider extends FabricDynamicRegistryProvider {
    public GGBannerPatternProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        add(registries, entries, GGBannerPatterns.GIPPLE);
    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<BannerPattern> resourceKey) {
        RegistryWrapper.Impl<BannerPattern> configuredFeatureRegistryLookup = registries.getOrThrow(RegistryKeys.BANNER_PATTERN);
        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "Banner Patterns";
    }
}