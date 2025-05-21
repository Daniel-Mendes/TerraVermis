package ch.daniel_mendes.terra_vermis.fabric;

import ch.daniel_mendes.terra_vermis.TerraVermisCommon;
import ch.daniel_mendes.terra_vermis.event.fabric.FabricBlockBreakListener;
import ch.daniel_mendes.terra_vermis.registry.worldgen.BiomeModificationRegistry;
import net.fabricmc.api.ModInitializer;

public class TerraVermisFabric implements ModInitializer {


    @Override
    public void onInitialize() {
        TerraVermisCommon.init();

        FabricBlockBreakListener.register();
        BiomeModificationRegistry.register();
    }
}
