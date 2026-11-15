package net.spartanweapons.neoforge.client;

import me.shedaniel.autoconfig.AutoConfigClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.config.SpartanWeaponsConfig;
import net.spartanweapons.init.RenderInit;

@Mod(value = SpartanWeaponsMain.MOD_ID, dist = Dist.CLIENT)
public class SpartanWeaponsNeoForgeClient {

    public SpartanWeaponsNeoForgeClient(IEventBus modBus, ModContainer container) {
        modBus.addListener(EntityRenderersEvent.RegisterRenderers.class, event -> RenderInit.init(event::registerEntityRenderer));
        container.registerExtensionPoint(IConfigScreenFactory.class,
                (modContainer, parent) -> AutoConfigClient.getConfigScreen(SpartanWeaponsConfig.class, parent).get());
    }

}
