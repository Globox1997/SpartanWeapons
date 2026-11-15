package net.spartanweapons.fabric.platform;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.spartanweapons.entity.JavelinEntity;
import net.spartanweapons.item.Javelin;
import net.spartanweapons.platform.services.IPlatformHelper;

import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public Javelin createJavelin(Supplier<EntityType<JavelinEntity>> typeSupplier, Item.Properties settings) {
        return new Javelin(typeSupplier, settings);
    }
}
