package net.digitalpear.gipples_galore.client;

import net.digitalpear.gipples_galore.common.entities.aneuploidian.AneuploidianEntityRenderer;
import net.digitalpear.gipples_galore.common.entities.gipple.GippleEntityRenderer;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class GipplesGaloreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(GGEntities.GIPPLE, GippleEntityRenderer::new);
        EntityRendererRegistry.register(GGEntities.ANEUPLOIDIAN, AneuploidianEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), GGBlocks.GELATIN_LAYER);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), GGBlocks.GIPPLEPAD, GGBlocks.GELATINOUS_GROWTH, GGBlocks.POTTED_GELATINOUS_GROWTH);
    }
}
