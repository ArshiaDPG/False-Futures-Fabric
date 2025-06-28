package net.digitalpear.gipples_galore.common.datagens.tags;

import net.digitalpear.gipples_galore.init.tags.GGEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class GGEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public GGEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getTagBuilder(GGEntityTypeTags.ANEUPLOIDIAN_TARGET_BLACKLIST)
                .add(getId(EntityType.CREAKING))
                .addOptionalTag(EntityTypeTags.CAN_BREATHE_UNDER_WATER.id())
                .add(getId(EntityType.CREEPER));
    }
    public static Identifier getId(EntityType<?> block){
        return Registries.ENTITY_TYPE.getId(block);
    }
}
