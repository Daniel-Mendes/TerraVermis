package ch.daniel_mendes.terra_vermis.mixin.entity.animal;

import ch.daniel_mendes.terra_vermis.entity.ai.goal.EatEarthwormBlockGoal;
import ch.daniel_mendes.terra_vermis.entity.animal.ChickenAccessor;
import ch.daniel_mendes.terra_vermis.mixin.entity.animal.util.EarthwormEatingLogic;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Pig.class)
public class PigAnimalMixin extends Animal implements ChickenAccessor {
    private int eatAnimationTick;
    private EatEarthwormBlockGoal eatEarthwormBlockGoal;

    protected PigAnimalMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ItemTags.PIG_FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
       return null;
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void addEarthwormEatingGoal(CallbackInfo ci) {
        this.eatEarthwormBlockGoal = new EatEarthwormBlockGoal(this);
        this.goalSelector.addGoal(EatEarthwormBlockGoal.EAT_GOAL_PRIORITY, this.eatEarthwormBlockGoal);
    }

    @Override
    protected void customServerAiStep(ServerLevel level) {
        this.eatAnimationTick = this.eatEarthwormBlockGoal.getEatAnimationTick();
        super.customServerAiStep(level);
    }

    @Override
    public void aiStep() {
        if (this.level().isClientSide) {
            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == EatEarthwormBlockGoal.EAT_ANIMATION_EVENT) {
            this.eatAnimationTick = EatEarthwormBlockGoal.EAT_ANIMATION_TICKS + this.random.nextInt(20);
            EarthwormEatingLogic.handleEatParticle(this.level(), this, this.random);
        } else {
            super.handleEntityEvent(id);
        }
    }

    public float terra_vermis$getHeadEatPositionScale(float partialTick) {
        if (this.eatAnimationTick <= 0) {
            return 0.0F;
        } else if (this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36) {
            return 1.0F;
        } else {
            return this.eatAnimationTick < 4
                    ? (this.eatAnimationTick - partialTick) / 4.0F
                    : -(this.eatAnimationTick - 40 - partialTick) / 4.0F;
        }
    }

    public float terra_vermis$getHeadEatAngleScale(float partialTick) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = (this.eatAnimationTick - 4 - partialTick) / 32.0F;
            return (float) (Math.PI / 5) + 0.21991149F * Mth.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0
                    ? (float) (Math.PI / 5)
                    : this.getXRot(partialTick) * ((float) Math.PI / 180.0F);
        }
    }
}
