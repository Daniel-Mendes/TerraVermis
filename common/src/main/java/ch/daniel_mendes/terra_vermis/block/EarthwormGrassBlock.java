package ch.daniel_mendes.terra_vermis.block;

import org.jetbrains.annotations.Nullable;

import ch.daniel_mendes.terra_vermis.block.util.WormDropLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EarthwormGrassBlock extends GrassBlock {
    public EarthwormGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerWillDestroy(level, pos, state, player);

        WormDropLogic.tryDroppingWorms(level, pos, state, player);
    }
}
