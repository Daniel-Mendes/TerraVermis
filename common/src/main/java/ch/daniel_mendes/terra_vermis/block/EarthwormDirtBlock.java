package ch.daniel_mendes.terra_vermis.block;

import ch.daniel_mendes.terra_vermis.block.util.WormDropLogic;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EarthwormDirtBlock extends Block {
    public EarthwormDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerWillDestroy(level, pos, state, player);

        WormDropLogic.tryDroppingWorms(level, pos, state, player);
    }
}