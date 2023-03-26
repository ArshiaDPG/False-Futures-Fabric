package net.digitalpear.falsefutures.common.entities.something;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SomethingEntityRenderer  extends GeoEntityRenderer<SomethingEntity> {
    public SomethingEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SomethingEntityModel());
    }

}