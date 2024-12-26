package net.digitalpear.gipples_galore.common.datagens.tags;

import net.digitalpear.gipples_galore.init.tags.GGEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class GGEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public GGEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(GGEntityTypeTags.ANEUPLOIDIAN_TARGET_BLACKLIST)
                .add(EntityType.CREAKING)
                .forceAddTag(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
                .add(EntityType.CREEPER);
    }
}
