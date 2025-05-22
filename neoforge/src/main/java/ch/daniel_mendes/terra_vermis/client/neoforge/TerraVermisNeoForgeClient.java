package ch.daniel_mendes.terra_vermis.client.neoforge;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.client.TerraVermisClient;
import ch.daniel_mendes.terra_vermis.platform.neoforge.NeoForgeClientPlatformHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TerraVermisNeoForgeClient {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        TerraVermisClient.init();
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        NeoForgeClientPlatformHelper.injectBlockColors(event);
    }
}
