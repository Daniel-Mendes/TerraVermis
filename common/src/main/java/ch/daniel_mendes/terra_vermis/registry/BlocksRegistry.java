package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.RegistrationProvider;
import ch.daniel_mendes.terra_vermis.RegistryObject;
import ch.daniel_mendes.terra_vermis.block.WormyDirtBlock;
import ch.daniel_mendes.terra_vermis.block.WormyFarmBlock;
import ch.daniel_mendes.terra_vermis.block.WormyGrassBlock;
import ch.daniel_mendes.terra_vermis.block.WormyPodzolBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MyceliumBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class BlocksRegistry {
    public static void init() {}

    protected static ResourceKey<Block> createBlockId(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    private static RegistryObject<Block, Block> registerBlock(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = factory.apply(properties.setId(resourceKey));

        return BLOCKS.register(resourceKey.location().getPath(), () -> block);
    }

    private static RegistryObject<Block, Block> registerBlock(ResourceKey<Block> resourceKey, BlockBehaviour.Properties properties) {
        return registerBlock(resourceKey, Block::new, properties);
    }

    private static RegistryObject<Block, Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        RegistryObject<Block, Block> reg = registerBlock(createBlockId(name), factory, properties);

        ItemsRegistry.ITEMS.register(name, () -> new BlockItem(reg.get(), new Item.Properties().setId(ItemsRegistry.createItemId(name))));

        return reg;
    }

    public static RegistryObject<Block, Block> registerBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, Block::new, properties);
    }

    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, Constants.MOD_ID);

    public static final RegistryObject<Block, Block> WORMY_DIRT = registerBlock("wormy_dirt", WormyDirtBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).noOcclusion());
    public static final RegistryObject<Block, Block> WORMY_GRASS_BLOCK = registerBlock("wormy_grass_block", WormyGrassBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).randomTicks());
    public static final RegistryObject<Block, Block> WORMY_FARMLAND = registerBlock("wormy_farmland", WormyFarmBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FARMLAND).randomTicks());
    public static final RegistryObject<Block, Block> WORMY_PODZOL = registerBlock("wormy_podzol", WormyPodzolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL));
    public static final RegistryObject<Block, Block> WORMY_MYCELIUM = registerBlock("wormy_mycelium", MyceliumBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MYCELIUM));
}