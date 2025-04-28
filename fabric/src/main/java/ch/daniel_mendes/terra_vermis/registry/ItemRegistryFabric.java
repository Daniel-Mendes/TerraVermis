package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ItemRegistryFabric {
    public static void init() {}

    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));

        properties.setId(itemKey);

        // Create the item instance.
        Item item = itemFactory.apply(properties);

        // Register the item.
        return Registry.register(BuiltInRegistries.ITEM, itemKey, item);

    }

    public static final Item EARTHWORM = register("earthworm", Item::new, new Item.Properties());
}
