package ch.daniel_mendes.terra_vermis;

import ch.daniel_mendes.terra_vermis.event.FabricBlockBreakListener;
import ch.daniel_mendes.terra_vermis.registry.CompostableItemRegistryFabric;
import ch.daniel_mendes.terra_vermis.registry.worldgen.BiomeModificationRegistry;
import net.fabricmc.api.ModInitializer;

public class TerraVermisFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        TerraVermisCommon.init();

        CompostableItemRegistryFabric.register();
        FabricBlockBreakListener.register();

        BiomeModificationRegistry.register();
    }
}
