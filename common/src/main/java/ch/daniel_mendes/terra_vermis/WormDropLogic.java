package ch.daniel_mendes.terra_vermis;

import ch.daniel_mendes.terra_vermis.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class WormDropLogic {
    private static final Set<Block> EARTH_BLOCKS = Set.of(
        Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT,
        Blocks.PODZOL, Blocks.ROOTED_DIRT,Blocks.MYCELIUM
    );

    public static void dropWorms(Level level, BlockPos pos, BlockState state, Player player) {
        if (!EARTH_BLOCKS.contains(state.getBlock())) return;

        float chance = 0.05f;
        if (level.isRainingAt(pos)) chance += 0.10f;

        ResourceKey<Enchantment> fortuneKey = Enchantments.FORTUNE;
        int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(level.holderLookup(fortuneKey.registryKey()).getOrThrow(
            fortuneKey
        ), player.getMainHandItem());
        chance += fortuneLevel * 0.03f;

        if (level.random.nextFloat() < chance) {
            int numberOfWorms = level.random.nextInt(3) + 1;
            for (int i = 0; i < numberOfWorms; i++) {
                ItemEntity wormDrop = new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ItemRegistry.EARTHWORM.get().getDefaultInstance()
                );
                level.addFreshEntity(wormDrop);
            }
        }
    }
}
