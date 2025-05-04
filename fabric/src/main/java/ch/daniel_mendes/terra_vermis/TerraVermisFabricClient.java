package ch.daniel_mendes.terra_vermis;

import ch.daniel_mendes.terra_vermis.registry.client.BlockColorsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class TerraVermisFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockColorsRegistry.init();
    }
}
