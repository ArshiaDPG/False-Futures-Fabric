package net.digitalpear.falsefutures.common.entities.gipple;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GippleEntityRenderer extends GeoEntityRenderer<GippleEntity> {
    public GippleEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GippleEntityModel());
    }
}