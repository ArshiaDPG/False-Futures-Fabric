package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AneuploidianEntityRenderer extends GeoEntityRenderer<AneuploidianEntity> {
    public AneuploidianEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new AneuploidianEntityModel());
    }

}