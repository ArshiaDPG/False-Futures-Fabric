package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.digitalpear.falsefutures.init.tags.FFItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;

public class FFBlockModelGen extends FabricModelProvider {
    public FFBlockModelGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerGipplePad(blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(FFItems.GELATIN, Models.GENERATED);
        itemModelGenerator.register(FFItems.GIPPLE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(FFItems.MUSIC_DISC_GIPPLECORE, Models.GENERATED);
    }
    private void registerGipplePad(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerItemModel(FFBlocks.GIPPLEPAD);
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(FFBlocks.GIPPLEPAD,
                ModelIds.getBlockModelId(FFBlocks.GIPPLEPAD)));
    }
}
