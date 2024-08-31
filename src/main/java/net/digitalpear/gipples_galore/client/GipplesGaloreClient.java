package net.digitalpear.gipples_galore.client;

import net.digitalpear.gipples_galore.common.entities.aneuploidian.AneuploidianEntityRenderer;
import net.digitalpear.gipples_galore.common.entities.gipple.GippleEntityRenderer;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGEntityTypes;
import net.digitalpear.gipples_galore.init.GGParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class GipplesGaloreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(GGEntityTypes.GIPPLE, GippleEntityRenderer::new);
        EntityRendererRegistry.register(GGEntityTypes.ANEUPLOIDIAN, AneuploidianEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), GGBlocks.GELATIN_LAYER);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), GGBlocks.GIPPLEPAD, GGBlocks.GELATINOUS_GROWTH, GGBlocks.POTTED_GELATINOUS_GROWTH);

        ParticleFactoryRegistry.getInstance().register(GGParticleTypes.GIPPLE, SpellParticle.DefaultFactory::new);
    }
}
