package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.tags.FFEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

public class FFEntityTypeTagGen extends FabricTagProvider<EntityType<?>> {

    public FFEntityTypeTagGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.ENTITY_TYPE);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(FFEntityTypeTags.SOMETHING_TARGET_BLACKLIST)
                .add(EntityType.CREEPER)
                .add(EntityType.COD)
                .add(EntityType.SQUID)
                .add(EntityType.GLOW_SQUID);
    }
}
