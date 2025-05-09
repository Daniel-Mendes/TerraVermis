package ch.daniel_mendes.terra_vermis.platform.fabric;

import ch.daniel_mendes.terra_vermis.platform.services.ICommonPlatformHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.Function;
import java.util.function.Supplier;

public class FabricCommonPlatformHelper implements ICommonPlatformHelper {
    @Override
    public <T extends Block> Supplier<T> registerBlock(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties) {
        T block = factory.apply(properties.setId(resourceKey));

        Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
        return () -> block;
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties properties) {
        return registerBlock(ICommonPlatformHelper.createBlockId(name), factory, properties);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Item.Properties properties) {
        Supplier<Item> registry = registerItem(ICommonPlatformHelper.createItemId(name), Item::new, properties);
        return  () -> (T) registry;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Function<Item.Properties, T> factory, Item.Properties properties) {
        return registerItem(ICommonPlatformHelper.createItemId(name), factory, properties);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(ResourceKey<Item> key, Function<Item.Properties, T> factory, Item.Properties properties) {
        T item = factory.apply(properties.setId(key));

        Registry.register(BuiltInRegistries.ITEM, key, item);
        return () -> item;
    }

    @Override
    public <T extends Feature<?>> Supplier<T> registerFeature(String name, Supplier<T> feature) {
        T registry = Registry.register(BuiltInRegistries.FEATURE, ICommonPlatformHelper.createFeatureId(name), feature.get());
        return () -> registry;
    }

    @Override
    public void registerCompostable(ItemLike item, float chance) {
        CompostingChanceRegistry.INSTANCE.add(item, chance);
    }
}
