package net.digitalpear.falsefutures.common.entities.gipple;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GippleEntityModel extends DefaultedEntityGeoModel<GippleEntity> {
    public static float BLOATED_SCALE = 1.3f;
    public static float CHILD_SCALE = 0.6f;

    public GippleEntityModel() {
        super(new Identifier(FalseFutures.MOD_ID, "gipple"));
    }

    @Override
    public Identifier getAnimationResource(GippleEntity entity) {
        return new Identifier(FalseFutures.MOD_ID, "animations/gipple.animation.json");
    }

    @Override
    public Identifier getModelResource(GippleEntity entity) {
        return new Identifier(FalseFutures.MOD_ID, "geo/gipple.geo.json");
    }

    @Override
    public Identifier getTextureResource(GippleEntity entity) {
        return new Identifier(FalseFutures.MOD_ID, "textures/entity/gipple.png");
    }

    @Override
    public void setCustomAnimations(GippleEntity animatable, long instanceId, AnimationState<GippleEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        CoreGeoBone body = this.getAnimationProcessor().getBone("Gipple");


        if (animatable.isDigesting()){
            body.setScaleX(BLOATED_SCALE);
            body.setScaleY(BLOATED_SCALE);
            body.setScaleZ(BLOATED_SCALE);
        }
        else if(animatable.isBaby()){
            body.setScaleX(CHILD_SCALE);
            body.setScaleY(CHILD_SCALE);
            body.setScaleZ(CHILD_SCALE);
        }
    }
}