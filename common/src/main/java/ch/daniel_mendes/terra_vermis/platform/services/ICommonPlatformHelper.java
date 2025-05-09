package ch.daniel_mendes.terra_vermis.platform.services;

import ch.daniel_mendes.terra_vermis.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
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

    static ResourceKey<Feature<?>> createFeatureId(String name) {
        return ResourceKey.create(Registries.FEATURE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    <T extends Block> Supplier<T> registerBlock(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties);

    <T extends Block> Supplier<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties);

    <T extends Item> Supplier<T> registerItem(String name, Item.Properties properties);

    <T extends Item> Supplier<T> registerItem(String name, Function<Item.Properties, T> factory, Item.Properties properties);

    <T extends Item> Supplier<T> registerItem(ResourceKey<Item> key, Function<Item.Properties, T> factory, Item.Properties properties);

    <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature);

    void registerCompostable(ItemLike item, float chance);
}
