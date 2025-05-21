package ch.daniel_mendes.terra_vermis;

import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import ch.daniel_mendes.terra_vermis.registry.CreativeModeTabsRegistry;
import ch.daniel_mendes.terra_vermis.registry.ItemsRegistry;
import ch.daniel_mendes.terra_vermis.registry.VillagerTradesRegistry;

public class TerraVermisCommon {

    public static void init() {
        ItemsRegistry.init();
        BlocksRegistry.init();
        CreativeModeTabsRegistry.init();
        //TagsRegistry.init();
        //CompostableItemRegistry.init();
        //FeaturesRegistry.init();
        VillagerTradesRegistry.init();
    }
}