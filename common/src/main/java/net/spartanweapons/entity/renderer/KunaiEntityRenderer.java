package net.spartanweapons.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.spartanweapons.entity.KunaiEntity;

public class KunaiEntityRenderer extends EntityRenderer<KunaiEntity, ThrownWeaponRenderState> {

    private final ItemModelResolver itemModelResolver;

    public KunaiEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemModelResolver = context.getItemModelResolver();
    }

    @Override
    public void submit(ThrownWeaponRenderState state, PoseStack matrixStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        matrixStack.pushPose();

        matrixStack.rotateDegrees(Axis.YP, state.yRot - 90.0F);
        matrixStack.rotateDegrees(Axis.ZP, state.xRot + -45.0F);

        matrixStack.translate(-0.3D, -0.15D, 0.0D);
        matrixStack.scale(0.85f,0.85f,0.85f);
        state.item.submit(matrixStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
        matrixStack.popPose();
        super.submit(state, matrixStack, submitNodeCollector, camera);
    }

    @Override
    public ThrownWeaponRenderState createRenderState() {
        return new ThrownWeaponRenderState();
    }

    @Override
    public void extractRenderState(KunaiEntity kunaiEntity, ThrownWeaponRenderState state, float partialTicks) {
        super.extractRenderState(kunaiEntity, state, partialTicks);
        state.yRot = kunaiEntity.getYRot(partialTicks);
        state.xRot = kunaiEntity.getXRot(partialTicks);
        // Use GUI type cause it isn't used for this type
        this.itemModelResolver.updateForNonLiving(state.item, kunaiEntity.getItem(), ItemDisplayContext.GUI, kunaiEntity);
    }

}
