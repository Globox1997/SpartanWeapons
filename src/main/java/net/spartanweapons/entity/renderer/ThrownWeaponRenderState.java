package net.spartanweapons.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.ThrownItemRenderState;

@Environment(EnvType.CLIENT)
public class ThrownWeaponRenderState extends ThrownItemRenderState {
    public float yRot;
    public float xRot;
}
