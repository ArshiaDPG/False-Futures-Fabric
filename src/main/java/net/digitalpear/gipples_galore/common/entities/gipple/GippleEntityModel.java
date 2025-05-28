package net.digitalpear.gipples_galore.common.entities.gipple;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class GippleEntityModel<T extends GippleEntity> extends DefaultedEntityGeoModel<T> {
    public GippleEntityModel() {
        super(GipplesGalore.id("gipple"));
    }

    @Override
    public Identifier getAnimationResource(T entity) {
        return GipplesGalore.id("geckolib/animations/gipple.animation.json");
    }

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return GipplesGalore.id("geckolib/models/gipple.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return GipplesGalore.id("textures/entity/gipple/blue.png");
    }
}