package ch.daniel_mendes.terra_vermis.trade.fabric;

import ch.daniel_mendes.terra_vermis.registry.ItemsRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

public class FabricVillagerOffers {
    public static void register() {
//        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
//            itemListings -> itemListings.add((trader, random) -> new MerchantOffer(
//                new ItemCost(Items.EMERALD, 2),
//                new ItemStack(ItemsRegistry.EARTHWORM.get(), 4),
//                10, 5, 0.05f
//            ))
//        );

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
            itemListings -> itemListings.add((trader, random) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 2),
                new ItemStack(ItemsRegistry.EARTHWORM.get(), 4),
                10, 5, 0.05f
            ))
        );

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
            itemListings -> itemListings.add((trader, random) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 5),
                new ItemStack(ItemsRegistry.FISHING_ROD_WITH_BAIT.get(), 1),
                5, 10, 0.2f
            ))
        );
    }
}
