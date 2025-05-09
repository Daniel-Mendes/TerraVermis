package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.platform.Services;

public class CompostableItemRegistry {

    public static void init() {
        Services.COMMON.registerCompostable(ItemsRegistry.EARTHWORM.get(), 0.3f);
    }
}
