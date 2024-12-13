package net.digitalpear.gipples_galore.common.datagens.providers;

import net.digitalpear.gipples_galore.init.GGJukeboxSongs;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class GGJukeboxSongProvider extends FabricDynamicRegistryProvider {
    public GGJukeboxSongProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        add(registries, entries, GGJukeboxSongs.GIPPLECORE);
    }


    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<JukeboxSong> resourceKey) {
        RegistryWrapper.Impl<JukeboxSong> configuredFeatureRegistryLookup = registries.getOrThrow(RegistryKeys.JUKEBOX_SONG);
        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "jukebox_song";
    }
}