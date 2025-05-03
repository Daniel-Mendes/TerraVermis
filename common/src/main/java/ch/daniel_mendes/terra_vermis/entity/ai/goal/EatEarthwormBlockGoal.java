package ch.daniel_mendes.terra_vermis.entity.ai.goal;

import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;
import java.util.function.Predicate;

public class EatEarthwormBlockGoal extends Goal {
    private static final int EAT_ANIMATION_TICKS = 40;
    private final Predicate<BlockState> IS_EDIBLE = state -> state.is(BlocksRegistry.EARTHWORM_DIRT.get()) || state.is(BlocksRegistry.EARTHWORM_GRASS_BLOCK.get());
    private final Mob mob;
    private final Level level;
    private int eatAnimationTick;

    public EatEarthwormBlockGoal(Mob mob) {
        this.mob = mob;
        this.level = mob.level();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = this.mob.blockPosition();
            return IS_EDIBLE.test(this.level.getBlockState(blockpos)) || IS_EDIBLE.test(this.level.getBlockState(blockpos.below()));
        }
    }

    @Override
    public void start() {
        this.eatAnimationTick = this.adjustedTickDelay(40);
        this.level.broadcastEntityEvent(this.mob, (byte)10);
        this.mob.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.eatAnimationTick = 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.eatAnimationTick > 0;
    }

    public int getEatAnimationTick() {
        return this.eatAnimationTick;
    }

    @Override
    public void tick() {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);

        if (this.eatAnimationTick == this.adjustedTickDelay(4)) {
            BlockPos blockpos = this.mob.blockPosition();
            BlockState blockState = this.level.getBlockState(blockpos);

            if (IS_EDIBLE.test(blockState)) {
                if (getServerLevel(this.level).getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {


                    if (blockState.is(BlocksRegistry.EARTHWORM_DIRT.get())) {
                        this.level.setBlockAndUpdate(blockpos, Blocks.DIRT.defaultBlockState());
                    } else if (blockState.is(BlocksRegistry.EARTHWORM_GRASS_BLOCK.get())) {
                        this.level.setBlockAndUpdate(blockpos, Blocks.GRASS_BLOCK.defaultBlockState());
                    }
                }

                this.mob.ate();
            } else {
                BlockPos blockposBellow = blockpos.below();
                BlockState blockStateBellow =this.level.getBlockState(blockposBellow);

                if(IS_EDIBLE.test(blockStateBellow)) {
                    if (getServerLevel(this.level).getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        if (blockStateBellow.is(BlocksRegistry.EARTHWORM_DIRT.get())) {
                            this.level.setBlockAndUpdate(blockposBellow, Blocks.DIRT.defaultBlockState());
                        } else if (blockStateBellow.is(BlocksRegistry.EARTHWORM_GRASS_BLOCK.get())) {
                            this.level.setBlockAndUpdate(blockposBellow, Blocks.GRASS_BLOCK.defaultBlockState());
                        }
                    }

                    this.mob.ate();
                }
            }
        }
    }
}
