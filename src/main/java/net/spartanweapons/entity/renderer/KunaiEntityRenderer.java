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
import net.spartanweapons.entity.KunaiEntity;

@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public class KunaiEntityRenderer extends EntityRenderer<KunaiEntity> {

    private final ItemRenderer itemRenderer;

    public KunaiEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(KunaiEntity kunaiEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, kunaiEntity.prevYaw, kunaiEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, kunaiEntity.prevPitch, kunaiEntity.getPitch()) + -45.0F));

        matrixStack.translate(-0.3D, -0.15D, 0.0D);
        matrixStack.scale(0.85f,0.85f,0.85f);
        // Use GUI type cause it isn't used for this type
        this.itemRenderer.renderItem(kunaiEntity.getStack(), ModelTransformationMode.GUI, i, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, kunaiEntity.getWorld(),
                kunaiEntity.getId());
        matrixStack.pop();
        super.render(kunaiEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(KunaiEntity kunaiEntity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }

}