package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.init.tags.FFItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

public class FFItemTagGeneration extends FabricTagProvider<Item> {
    public FFItemTagGeneration(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.ITEM);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(FFItemTags.GIPPLE_FOOD).add(Items.GLOW_LICHEN);
    }
}
