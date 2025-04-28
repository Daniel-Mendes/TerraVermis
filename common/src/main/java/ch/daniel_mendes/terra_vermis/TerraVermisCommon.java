package ch.daniel_mendes.terra_vermis;

import ch.daniel_mendes.terra_vermis.registry.BlockRegistry;
import ch.daniel_mendes.terra_vermis.registry.CreativeModeTabRegistry;
import ch.daniel_mendes.terra_vermis.registry.ItemRegistry;

public class TerraVermisCommon {

    public static void init() {
        ItemRegistry.init();
        BlockRegistry.init();
        CreativeModeTabRegistry.init();
    }
}