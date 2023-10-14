package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AneuploidianEntityModel extends DefaultedEntityGeoModel<AneuploidianEntity> {

    public AneuploidianEntityModel() {
        super(new Identifier(GipplesGalore.MOD_ID, "aneuploidian"));
    }

    @Override
    public Identifier getAnimationResource(AneuploidianEntity entity) {
        return new Identifier(GipplesGalore.MOD_ID, "animations/aneuploidian.animation.json");
    }

    @Override
    public Identifier getModelResource(AneuploidianEntity entity) {
        return new Identifier(GipplesGalore.MOD_ID, "geo/aneuploidian.geo.json");
    }

    @Override
    public Identifier getTextureResource(AneuploidianEntity entity) {
        return new Identifier(GipplesGalore.MOD_ID, "textures/entity/aneuploidian.png");
    }

}