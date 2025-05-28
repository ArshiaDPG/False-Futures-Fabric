package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class AneuploidianEntityRenderer<T extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<AneuploidianEntity, T> {
    public AneuploidianEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new AneuploidianEntityModel());
    }
}