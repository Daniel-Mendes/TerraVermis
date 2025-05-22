package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.block.*;
import ch.daniel_mendes.terra_vermis.platform.Services;
import ch.daniel_mendes.terra_vermis.platform.services.ICommonPlatformHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MyceliumBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

public class BlocksRegistry {
    public static void init() {}

    public static Supplier<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Supplier<Block> toReturn = Services.COMMON.registerBlock(name, factory, properties);
        Services.COMMON.registerItem(name, () -> new BlockItem(toReturn.get(), new Item.Properties().setId(ICommonPlatformHelper.createItemId(name))));
        return toReturn;
    }

    public static final Supplier<Block> WORMY_DIRT = registerBlock("wormy_dirt", WormyDirtBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).noOcclusion());
    public static final Supplier<Block> WORMY_GRASS_BLOCK = registerBlock("wormy_grass_block", WormyGrassBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).randomTicks());
    public static final Supplier<Block> WORMY_FARMLAND = registerBlock("wormy_farmland", WormyFarmBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FARMLAND).randomTicks());
    public static final Supplier<Block> WORMY_PODZOL = registerBlock("wormy_podzol", WormyPodzolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL));
    public static final Supplier<Block> WORMY_MYCELIUM = registerBlock("wormy_mycelium", WormyMyceliumBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MYCELIUM));
}