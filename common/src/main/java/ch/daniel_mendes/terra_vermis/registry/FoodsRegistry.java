package ch.daniel_mendes.terra_vermis.registry;

import net.minecraft.world.food.FoodProperties;

public class FoodsRegistry {

    public static void init() {}

    public static final FoodProperties EARTHWORM_STEW = stew(10).build();

    private static FoodProperties.Builder stew(int nutrition) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(0.6F);
    }
}
