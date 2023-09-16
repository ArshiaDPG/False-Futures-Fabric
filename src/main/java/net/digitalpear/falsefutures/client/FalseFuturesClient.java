package net.digitalpear.falsefutures.client;

import net.digitalpear.falsefutures.common.entities.gipple.GippleEntityRenderer;
import net.digitalpear.falsefutures.common.entities.something.SomethingEntityRenderer;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class FalseFuturesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(FFEntities.GIPPLE, GippleEntityRenderer::new);
        EntityRendererRegistry.register(FFEntities.SOMETHING, SomethingEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), FFBlocks.GELATIN_LAYER, FFBlocks.HIBERNATING_GIPPLE);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), FFBlocks.GIPPLEPAD, FFBlocks.JELLYROOT, FFBlocks.POTTED_JELLYROOT, FFBlocks.TALL_JELLYROOT);
    }
}
