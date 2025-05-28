package net.digitalpear.gipples_galore.common.entities.gipple;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GippleEntityRenderer<T extends GippleEntity> extends GeoEntityRenderer<T, GippleRenderState> {

    public GippleEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GippleEntityModel<>());
    }

    @Override
    public void preRender(GippleRenderState renderState, MatrixStack poseStack, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, int packedLight, int packedOverlay, int renderColor) {
        super.preRender(renderState, poseStack, model, bufferSource, buffer, isReRender, packedLight, packedOverlay, renderColor);
        poseStack.scale(renderState.baseScale, renderState.baseScale, renderState.baseScale);
        this.shadowRadius *= renderState.baseScale;
    }

    @Override
    public void updateRenderState(T entity, EntityRenderState entityRenderState, float partialTick) {
        super.updateRenderState(entity, entityRenderState, partialTick);
        if (entityRenderState instanceof GippleRenderState gippleRenderState){
            gippleRenderState.setLuminous(entity.isLuminous());
        }
    }

    @Override
    public void actuallyRender(GippleRenderState renderState, MatrixStack poseStack, BakedGeoModel model, @Nullable RenderLayer renderType, VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, int packedLight, int packedOverlay, int renderColor) {
        poseStack.push();
        if (renderState.isLuminous()) {
            packedLight = 255;
            packedOverlay = OverlayTexture.DEFAULT_UV;
            renderColor = 16777215;
        }
        super.actuallyRender(renderState, poseStack, model, renderType, bufferSource, buffer, isReRender, packedLight, packedOverlay, renderColor);
        poseStack.pop();
    }
}