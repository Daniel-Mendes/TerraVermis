package ch.daniel_mendes.terra_vermis.platform.fabric;

import ch.daniel_mendes.terra_vermis.mixin.fabric.CropBlockAccessorFabric;
import ch.daniel_mendes.terra_vermis.platform.services.ICommonPlatformHelper;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.Collections;
import java.util.function.Function;
import java.util.function.Supplier;

public class FabricCommonPlatformHelper implements ICommonPlatformHelper {
    @Override
    public Supplier<Block> registerBlock(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = factory.apply(properties.setId(resourceKey));

        Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
        return () -> block;
    }

    @Override
    public Supplier<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return registerBlock(ICommonPlatformHelper.createBlockId(name), factory, properties);
    }

    @Override
    public void registerItem(String name, Supplier<Item> item) {
       Registry.register(BuiltInRegistries.ITEM, ICommonPlatformHelper.createItemId(name), item.get());
    }

    @Override
    public Supplier<Item> registerItem(String name, Item.Properties properties) {
        return registerItem(ICommonPlatformHelper.createItemId(name), Item::new, properties);
    }

    @Override
    public Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        return registerItem(ICommonPlatformHelper.createItemId(name), factory, properties);
    }

    @Override
    public Supplier<Item> registerItem(ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties properties) {
        Item item = factory.apply(properties.setId(key));

        Registry.register(BuiltInRegistries.ITEM, key, item);
        return () -> item;
    }

    @Override
    public <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature) {
        T registry = Registry.register(BuiltInRegistries.FEATURE, ICommonPlatformHelper.createFeatureId(name), feature.get());
        return () -> registry;
    }

    @Override
    public void registerVillagerOffers(ResourceKey<VillagerProfession> profession, int level, VillagerTrades.ItemListing... trades) {
        TradeOfferHelper.registerVillagerOffers(profession, level, c -> Collections.addAll(c, trades));
    }

    @Override
    public void registerCompostable(Supplier<Item> item, float chance) {
        CompostingChanceRegistry.INSTANCE.add(item.get(), chance);
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeModeTab(String name, Supplier<CreativeModeTab> tab) {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ICommonPlatformHelper.createCreativeModeTabId(name), tab.get());

        return tab;
    }

    @Override
    public float getGrowthSpeed(Block block, BlockGetter level, BlockPos pos) {
        return CropBlockAccessorFabric.callGetGrowthSpeed(block, level, pos);
    }
}
