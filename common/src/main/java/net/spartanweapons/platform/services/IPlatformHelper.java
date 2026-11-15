package net.spartanweapons.platform.services;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.spartanweapons.entity.JavelinEntity;
import net.spartanweapons.item.Javelin;

import java.util.function.Supplier;

public interface IPlatformHelper {

    Javelin createJavelin(Supplier<EntityType<JavelinEntity>> typeSupplier, Item.Properties settings);
}
