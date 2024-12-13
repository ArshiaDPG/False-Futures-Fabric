package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.digitalpear.gipples_galore.init.tags.GGEntityTypeTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class AneuploidianTargetGoal<T extends LivingEntity> extends TrackTargetGoal {
    private static final int DEFAULT_RECIPROCAL_CHANCE = 10;
    protected final Class<T> targetClass;
    protected final int reciprocalChance;
    @Nullable
    protected LivingEntity targetEntity;
    protected TargetPredicate targetPredicate;

    public AneuploidianTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility) {
        this(mob, targetClass, DEFAULT_RECIPROCAL_CHANCE, checkVisibility, false, null);
    }

    public AneuploidianTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility, TargetPredicate.EntityPredicate targetPredicate) {
        this(mob, targetClass, DEFAULT_RECIPROCAL_CHANCE, checkVisibility, false, targetPredicate);
    }

    public AneuploidianTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility, boolean checkCanNavigate) {
        this(mob, targetClass, DEFAULT_RECIPROCAL_CHANCE, checkVisibility, checkCanNavigate, null);
    }

    public AneuploidianTargetGoal(MobEntity mob, Class<T> targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, @Nullable TargetPredicate.EntityPredicate targetPredicate) {
        super(mob, checkVisibility, checkCanNavigate);
        this.targetClass = targetClass;
        this.reciprocalChance = toGoalTicks(reciprocalChance);
        this.setControls(EnumSet.of(Control.TARGET));
        this.targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(this.getFollowRange()).setPredicate(targetPredicate);
    }

    public boolean canStart() {
        if (this.reciprocalChance > 0 && this.mob.getRandom().nextInt(this.reciprocalChance) != 0) {
            return false;
        } else {
            this.findClosestTarget();
            return (this.targetEntity != null) && (!this.targetEntity.isSubmergedInWater()) && !this.targetEntity.getType().isIn(GGEntityTypeTags.ANEUPLOIDIAN_TARGET_BLACKLIST);
        }
    }

    protected Box getSearchBox(double distance) {
        return this.mob.getBoundingBox().expand(distance, 4.0D, distance);
    }

    protected void findClosestTarget() {
        ServerWorld serverWorld = getServerWorld(this.mob);
        if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {
            this.targetEntity = serverWorld.getClosestEntity(this.mob.getWorld().getEntitiesByClass(this.targetClass, this.getSearchBox(this.getFollowRange()), (livingEntity) -> true), this.getAndUpdateTargetPredicate(), this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        } else {
            this.targetEntity = serverWorld.getClosestPlayer(this.getAndUpdateTargetPredicate(), this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }

    }

    public void start() {
        this.mob.setTarget(this.targetEntity);
        super.start();
    }
    private TargetPredicate getAndUpdateTargetPredicate() {
        return this.targetPredicate.setBaseMaxDistance(this.getFollowRange());
    }
}
