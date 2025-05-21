package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.item.FishingRodWithBaitItem;
import ch.daniel_mendes.terra_vermis.platform.Services;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.function.Supplier;

public class ItemsRegistry {
    public static void init() {}

    public static final Supplier<Item> EARTHWORM = Services.COMMON.registerItem("earthworm", new Item.Properties()
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

    public static final Supplier<Item> FISHING_ROD_WITH_BAIT = Services.COMMON.registerItem("fishing_rod_with_bait", FishingRodWithBaitItem::new, new Item.Properties().durability(64).enchantable(1));
}