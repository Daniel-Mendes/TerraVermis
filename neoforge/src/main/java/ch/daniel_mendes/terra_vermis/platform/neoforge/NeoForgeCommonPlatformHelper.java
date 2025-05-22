package ch.daniel_mendes.terra_vermis.platform.neoforge;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.mixin.neoforge.CropBlockAccessorNeoForge;
import ch.daniel_mendes.terra_vermis.platform.services.ICommonPlatformHelper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class NeoForgeCommonPlatformHelper implements ICommonPlatformHelper {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
        BuiltInRegistries.BLOCK,
        Constants.MOD_ID
    );
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Constants.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TAB = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, Constants.MOD_ID);
    public static final Map<ResourceKey<VillagerProfession>, Int2ObjectMap<List<VillagerTrades.ItemListing>>> TRADES = new HashMap<>();

    @Override
    public Supplier<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return registerBlock(ICommonPlatformHelper.createBlockId(name), factory, properties);
    }

    @Override
    public Supplier<Block> registerBlock(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return BLOCKS.register(key.location().getPath(), () -> factory.apply(properties.setId(key)));
    }

    @Override
    public void registerItem(String name, Supplier<Item> item) {
        ITEMS.register(name, item);
    }

    @Override
    public Supplier<Item> registerItem(String name, Item.Properties properties) {
        return registerItem(name, Item::new, properties);
    }

    @Override
    public Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        return registerItem(ICommonPlatformHelper.createItemId(name), factory, properties);
    }

    @Override
    public Supplier<Item> registerItem(ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties properties) {
        return ITEMS.register(key.location().getPath(), () -> factory.apply(properties.setId(key)));
    }

    @Override
    public <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature) {
        return null;
    }

    @Override
    public void registerVillagerOffers(ResourceKey<VillagerProfession> profession, int level, VillagerTrades.ItemListing... trades) {
        var map = TRADES.computeIfAbsent(profession, prof -> new Int2ObjectOpenHashMap<>());
        var tradesList = map.computeIfAbsent(level, l -> new ArrayList<>());
        Collections.addAll(tradesList, trades);
    }

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent e) {
        var map = TRADES.get(e.getType());

        if (map != null) {
            for (Int2ObjectMap.Entry<List<VillagerTrades.ItemListing>> listEntry : map.int2ObjectEntrySet()) {
                e.getTrades().computeIfAbsent(listEntry.getIntKey(), l -> new ArrayList<>()).addAll(listEntry.getValue());
            }
        }
    }

    @Override
    public void registerCompostable(Supplier<Item> item, float chance) {
       // DEPRECATED in flavor of data_maps
        ComposterBlock.COMPOSTABLES.put(() -> item.get(), chance);
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeModeTab(String name, Supplier<CreativeModeTab> tab) {
        return CREATIVE_MOD_TAB.register(name, tab);
    }

    @Override
    public float getGrowthSpeed(Block block, BlockGetter level, BlockPos pos) {
        return CropBlockAccessorNeoForge.callGetGrowthSpeed(block.defaultBlockState(), level, pos);
    }
}
