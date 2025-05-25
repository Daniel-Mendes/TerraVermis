package ch.daniel_mendes.terra_vermis.client;

import ch.daniel_mendes.terra_vermis.registry.client.BlockColorsRegistry;
import ch.daniel_mendes.terra_vermis.registry.client.BlockRenderLayers;
import ch.daniel_mendes.terra_vermis.registry.client.EntityRenderersRegistry;

public class TerraVermisClient {
    public static void init() {
        BlockRenderLayers.init();
        BlockColorsRegistry.init();
        EntityRenderersRegistry.init();
    }
}
