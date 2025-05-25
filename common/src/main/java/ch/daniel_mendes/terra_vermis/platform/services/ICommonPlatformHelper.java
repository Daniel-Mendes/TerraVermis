package ch.daniel_mendes.terra_vermis.platform.services;

import ch.daniel_mendes.terra_vermis.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.Function;
import java.util.function.Supplier;

public interface ICommonPlatformHelper {

    static ResourceKey<Block> createBlockId(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    static ResourceKey<Item> createItemId(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    static ResourceKey<CreativeModeTab> createCreativeModeTabId(String name) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    static ResourceKey<Feature<?>> createFeatureId(String name) {
        return ResourceKey.create(Registries.FEATURE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    static ResourceKey<EntityType<?>> createEntityTypeId(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    Supplier<Block> registerBlock(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties);

    Supplier<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties);

    void registerItem(String name, Supplier<Item> item);

    Supplier<Item> registerItem(String name, Item.Properties properties);

    Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties);

    Supplier<Item> registerItem(ResourceKey<Item> id, Function<Item.Properties, Item> factory, Item.Properties properties);

    <T extends Entity> Supplier<EntityType<T>> registerEntityType(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, int clientTrackingRange);

    <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature);

    void registerVillagerOffers(ResourceKey<VillagerProfession> profession, int level, VillagerTrades.ItemListing... trades);

    void registerCompostable(Supplier<Item> item, float chance);

    Supplier<CreativeModeTab> registerCreativeModeTab(String name, Supplier<CreativeModeTab> tab);

    float getGrowthSpeed(Block block, BlockGetter level, BlockPos pos);
}
