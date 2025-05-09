package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.RegistrationProvider;
import ch.daniel_mendes.terra_vermis.RegistryObject;
import ch.daniel_mendes.terra_vermis.item.FishingRodWithBaitItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.function.Function;

public class ItemsRegistry {
    public static void init() {}

    protected static ResourceKey<Item> createItemId(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    public static RegistryObject<Item, Item> registerItem(String name, Item.Properties properties) {
        return registerItem(createItemId(name), Item::new, properties);
    }

    public static RegistryObject<Item, Item> registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        return registerItem(createItemId(name), factory, properties);
    }

    public static RegistryObject<Item, Item> registerItem(ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties properties) {
        Item item = factory.apply(properties.setId(key));

        return ITEMS.register(key.location().getPath(), () -> item);
    }

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MOD_ID);

    public static final RegistryObject<Item, Item> EARTHWORM = registerItem("earthworm", new Item.Properties()
                .stacksTo(64)
                .food(new FoodProperties.Builder()
                                .nutrition(1)
                                .saturationModifier(0.3f)
                                .build(),
                        Consumables.defaultFood()
                                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F))
                                .build()
                )
    );

    public static final RegistryObject<Item, Item> FISHING_ROD_WITH_BAIT = registerItem("fishing_rod_with_bait", FishingRodWithBaitItem::new, new Item.Properties().durability(64).enchantable(1));
}