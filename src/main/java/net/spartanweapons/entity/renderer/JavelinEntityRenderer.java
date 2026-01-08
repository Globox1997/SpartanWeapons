package net.spartanweapons.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.spartanweapons.entity.JavelinEntity;

@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public class JavelinEntityRenderer extends EntityRenderer<JavelinEntity> {

    private final ItemRenderer itemRenderer;

    public JavelinEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(JavelinEntity javelinEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, javelinEntity.prevYaw, javelinEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, javelinEntity.prevPitch, javelinEntity.getPitch()) + -45.0F));

        matrixStack.translate(-0.3D, -0.15D, 0.0D);
        matrixStack.scale(1.47f,1.47f,0.85f);
        // Use GUI type cause it isn't used for this type
        this.itemRenderer.renderItem(javelinEntity.getStack(), ModelTransformationMode.GUI, i, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, javelinEntity.getWorld(),
                javelinEntity.getId());
        matrixStack.pop();
        super.render(javelinEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(JavelinEntity javelinEntity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }

}