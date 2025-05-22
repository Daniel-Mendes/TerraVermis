package ch.daniel_mendes.terra_vermis.block.util;

import ch.daniel_mendes.terra_vermis.registry.ItemsRegistry;
import ch.daniel_mendes.terra_vermis.registry.TagsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class WormDropLogic {
    private static final float BASE_CHANCE = 0.05f;
    private static final float WORMY_CHANCE = 0.20f;
    private static final float FERTILE_CHANCE = 0.10f;
    private static final float HIBERNATION_CHANCE = -0.05f;
    private static final float RAINING_CHANCE = 0.10f;
    private static final float FORTUNE_CHANCE = 0.03f;

    private static final TagKey<Block> FERTILE_BLOCKS = TagsRegistry.BlockTags.FERTILE_BLOCKS;
    private static final TagKey<Block> HIBERNATION_BLOCKS = TagsRegistry.BlockTags.HIBERNATION_BLOCKS;
    private static final TagKey<Block> MUDDY_BLOCKS = TagsRegistry.BlockTags.MUDDY_BLOCKS;
    private static final TagKey<Block> WORMY_BLOCKS = TagsRegistry.BlockTags.WORMY_BLOCKS;

    public static void tryDroppingWorms(LevelAccessor levelAccessor, BlockPos pos, BlockState state, Player player) {
        if (!(levelAccessor instanceof Level level)) return;

        boolean isFertileBlock = state.is(FERTILE_BLOCKS);
        boolean isHibernationBlock = state.hasProperty(BlockStateProperties.SNOWY) && state.getValue(BlockStateProperties.SNOWY) && state.is(HIBERNATION_BLOCKS);
        boolean isMuddyBlock = state.is(MUDDY_BLOCKS);
        boolean isWormyBlock = state.is(WORMY_BLOCKS);
        boolean isRaining = level.isRainingAt(pos);

        if (!isMuddyBlock && !isWormyBlock) return;

        float chance = BASE_CHANCE;

        if (isWormyBlock) chance += WORMY_CHANCE;
        if (isFertileBlock) chance += FERTILE_CHANCE;
        if (isHibernationBlock) chance += HIBERNATION_CHANCE;
        if (isRaining) chance += RAINING_CHANCE;

        ResourceKey<Enchantment> fortuneKey = Enchantments.FORTUNE;
        int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(level.holderLookup(Enchantments.FORTUNE.registryKey()).getOrThrow(
            fortuneKey
        ), player.getMainHandItem());
        chance += fortuneLevel * FORTUNE_CHANCE;

        if (level.getRandom().nextFloat() > chance) return;

        DropProfile profile = DropProfile.getDropProfile(isHibernationBlock, isFertileBlock, isWormyBlock, isMuddyBlock, isRaining);
        int dropCount = weightedRandom(level, profile.min(), profile.max(), profile.lowChance());

        if (dropCount == 0) return;

        ItemStack stack = new ItemStack(ItemsRegistry.EARTHWORM.get(), dropCount);
        level.addFreshEntity(new ItemEntity(
            level,
            pos.getX() + 0.5,
            pos.getY() + 0.5,
            pos.getZ() + 0.5,
            stack
        ));
    }

    private static int weightedRandom(Level level, int low, int high, float lowChance) {
        return level.getRandom().nextFloat() < lowChance ? low : high;
    }
}
