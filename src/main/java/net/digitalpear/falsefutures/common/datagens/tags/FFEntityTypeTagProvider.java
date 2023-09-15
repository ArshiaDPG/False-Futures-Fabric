package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.tags.FFEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class FFEntityTypeTagProvider extends FabricTagProvider<EntityType<?>> {


    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public FFEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENTITY_TYPE, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(FFEntityTypeTags.SOMETHING_TARGET_BLACKLIST)
                .add(EntityType.CREEPER)
                .add(EntityType.COD)
                .add(EntityType.SQUID)
                .add(EntityType.GLOW_SQUID)
                .add(EntityType.AXOLOTL);
    }
}
