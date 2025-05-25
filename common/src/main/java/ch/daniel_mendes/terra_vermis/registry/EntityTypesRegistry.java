package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.entity.projectile.FishingHookWithBait;
import ch.daniel_mendes.terra_vermis.platform.Services;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class EntityTypesRegistry {
    public static void init() {}

    public static final Supplier<EntityType<FishingHookWithBait>> FISHING_BOBBER_WITH_BAIT = Services.COMMON.registerEntityType("fishing_bobber_with_bait", FishingHookWithBait::new, MobCategory.MISC, 0.75F, 0.75F, 4);
}
