package net.spartanweapons.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.spartanweapons.config.SpartanWeaponsConfig;

public class ConfigInit {

    public static SpartanWeaponsConfig CONFIG = new SpartanWeaponsConfig();

    public static void init() {
        AutoConfig.register(SpartanWeaponsConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(SpartanWeaponsConfig.class).getConfig();
    }

}
