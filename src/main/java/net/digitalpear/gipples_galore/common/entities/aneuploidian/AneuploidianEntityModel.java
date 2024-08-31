package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AneuploidianEntityModel extends DefaultedEntityGeoModel<AneuploidianEntity> {

    public AneuploidianEntityModel() {
        super(GipplesGalore.id("aneuploidian"));
    }

    @Override
    public Identifier getAnimationResource(AneuploidianEntity entity) {
        return GipplesGalore.id("animations/aneuploidian.animation.json");
    }

    @Override
    public Identifier getModelResource(AneuploidianEntity entity) {
        return GipplesGalore.id("geo/aneuploidian.geo.json");
    }

    @Override
    public Identifier getTextureResource(AneuploidianEntity entity) {
        return GipplesGalore.id("textures/entity/aneuploidian.png");
    }
}