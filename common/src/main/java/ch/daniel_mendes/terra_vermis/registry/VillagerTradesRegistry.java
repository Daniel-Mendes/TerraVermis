package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.platform.Services;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

public class VillagerTradesRegistry {

    public static void init() {
        Services.COMMON.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
            (trader, random) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 2),
                new ItemStack(ItemsRegistry.EARTHWORM.get(), 4),
                10, 5, 0.05f
            )
        );

        Services.COMMON.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
            (trader, random) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 5),
                new ItemStack(ItemsRegistry.FISHING_ROD_WITH_BAIT.get(), 1),
                5, 10, 0.2f
            )
        );
    }
}
