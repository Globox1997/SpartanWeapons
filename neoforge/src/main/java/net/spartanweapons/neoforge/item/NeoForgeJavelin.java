package net.spartanweapons.neoforge.item;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.spartanweapons.entity.JavelinEntity;
import net.spartanweapons.init.EventInit;
import net.spartanweapons.item.Javelin;

import java.util.function.Supplier;

public class NeoForgeJavelin extends Javelin {

    public NeoForgeJavelin(Supplier<EntityType<JavelinEntity>> typeSupplier, Properties settings) {
        super(typeSupplier, settings);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return EventInit.isJavelinEnchantment(stack, enchantment) || super.supportsEnchantment(stack, enchantment);
    }

    @Override
    public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
        return EventInit.isJavelinEnchantment(stack, enchantment) || super.isPrimaryItemFor(stack, enchantment);
    }
}
