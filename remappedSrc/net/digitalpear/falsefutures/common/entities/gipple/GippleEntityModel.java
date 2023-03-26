package net.digitalpear.falsefutures.common.entities.gipple;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class GippleEntityModel extends AnimatedTickingGeoModel<GippleEntity> {
    public static float BLOATED_SCALE = 1.3f;
    public static float CHILD_SCALE = 0.6f;

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
    public void setLivingAnimations(GippleEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("Gipple");


        if (entity.isDigesting()){
            body.setScaleX(BLOATED_SCALE);
            body.setScaleY(BLOATED_SCALE);
            body.setScaleZ(BLOATED_SCALE);
        }
        else if(entity.isBaby()){
            body.setScaleX(CHILD_SCALE);
            body.setScaleY(CHILD_SCALE);
            body.setScaleZ(CHILD_SCALE);
        }
    }

}