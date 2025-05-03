package ch.daniel_mendes.terra_vermis.registry;


import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

public class CompostableItemRegistryFabric {
    public static void register() {
        CompostingChanceRegistry.INSTANCE.add(ItemsRegistry.EARTHWORM.get(), 0.3f);
    }
}

