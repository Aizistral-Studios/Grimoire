package io.github.crucible.grimoire.mc1_12;

import io.github.crucible.grimoire.common.core.GrimoireCore;
import io.github.crucible.grimoire.common.integrations.ModIntegrationRegistry;
import io.github.crucible.grimoire.mc1_12.handlers.IncelEventHandler;
import io.github.crucible.grimoire.mc1_12.handlers.IncelPacketDispatcher;
import io.github.crucible.grimoire.mc1_12.integrations.eventhelper.EHIntegrationContainer;
import io.github.crucible.grimoire.mc1_12.network.PacketSyncOmniconfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = GrimoireMod.MODID, name = GrimoireMod.NAME, version = GrimoireMod.VERSION)
public class GrimoireMod {
    public static final String MODID = "grimoire";
    public static final String NAME = "Grimoire";
    public static final String VERSION = "@VERSION@";

    public static SimpleNetworkWrapper packetPipeline;

    public GrimoireMod() {
        // NO-OP
    }

    @Mod.EventHandler
    public final void serverStarted(FMLServerStartedEvent event) {
        // IntegrationManager.getEventHelperIntegration().initInjections();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        IncelPacketDispatcher.INSTANCE.getClass();

        IncelEventHandler handler = new IncelEventHandler();
        MinecraftForge.EVENT_BUS.register(handler);

        packetPipeline = new SimpleNetworkWrapper("GrimoireChannel");
        packetPipeline.registerMessage(PacketSyncOmniconfig.Handler.class, PacketSyncOmniconfig.class, 0, Side.CLIENT);

        // Init grimmix integrations
        ModIntegrationRegistry.registerIntegration(EHIntegrationContainer.class);
        ModIntegrationRegistry.init();
    }

}
