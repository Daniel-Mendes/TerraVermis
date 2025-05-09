package ch.daniel_mendes.terra_vermis;

import ch.daniel_mendes.terra_vermis.registry.*;

public class TerraVermisCommon {

    public static void init() {
        ItemsRegistry.init();
        BlocksRegistry.init();
        CreativeModeTabsRegistry.init();
        TagsRegistry.init();
        CompostableItemRegistry.init();
        FeaturesRegistry.init();
    }
}