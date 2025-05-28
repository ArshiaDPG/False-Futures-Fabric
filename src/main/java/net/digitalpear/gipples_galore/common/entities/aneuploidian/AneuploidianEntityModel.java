package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class AneuploidianEntityModel extends DefaultedEntityGeoModel<AneuploidianEntity> {

    public AneuploidianEntityModel() {
        super(GipplesGalore.id("aneuploidian"));
    }

    @Override
    public Identifier getAnimationResource(AneuploidianEntity entity) {
        return GipplesGalore.id("geckolib/animations/aneuploidian.animation.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return GipplesGalore.id("textures/entity/aneuploidian.png");
    }

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return GipplesGalore.id("geckolib/models/aneuploidian.geo.json");
    }

}