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
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

@Environment(EnvType.CLIENT)
public class FalseFuturesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(FFEntities.GIPPLE, GippleEntityRenderer::new);
        EntityRendererRegistry.register(FFEntities.SOMETHING, SomethingEntityRenderer::new);


        BlockRenderLayerMap.INSTANCE.putBlock(FFBlocks.GIPPLEPAD, RenderLayer.getCutout());


    }
}
