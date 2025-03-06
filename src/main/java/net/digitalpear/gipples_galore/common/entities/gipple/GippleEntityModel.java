package net.digitalpear.gipples_galore.common.entities.gipple;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class GippleEntityModel<T extends GippleEntity> extends DefaultedEntityGeoModel<T> {
    public GippleEntityModel() {
        super(GipplesGalore.id("gipple"));
    }

    @Override
    public Identifier getAnimationResource(T entity) {
        return GipplesGalore.id("animations/gipple.animation.json");
    }

    @Override
    public Identifier getModelResource(T entity, GeoRenderer<T> renderer) {
        return GipplesGalore.id("geo/gipple.geo.json");
    }

    @Override
    public Identifier getTextureResource(T entity, GeoRenderer<T> renderer) {
        return GipplesGalore.id("textures/entity/gipple/blue.png");
    }
}