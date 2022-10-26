package net.digitalpear.falsefutures.common.entities.mega_gipple;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class SomethingEntity extends HostileEntity implements Monster, Flutterer {
    protected SomethingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.lookControl = new LookControl(this);
        this.moveControl = new FlightMoveControl(this, 20, true);
        this.experiencePoints = 20;
    }
    protected void initGoals() {
        this.goalSelector.add(0, new SomethingAttackGoal(this, 1.0D, false));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal(this, LivingEntity.class, false));
    }


    @Override
    public boolean isInAir() {
        return !isOnGround();
    }
}
class SomethingAttackGoal extends MeleeAttackGoal {
    private final SomethingEntity gipple;
    private int ticks;

    public SomethingAttackGoal(SomethingEntity gipple, double speed, boolean pauseWhenMobIdle) {
        super(gipple, speed, pauseWhenMobIdle);
        this.gipple = gipple;
    }

    public void start() {
        super.start();
        this.ticks = 0;
    }

    public void stop() {
        super.stop();
        this.gipple.setAttacking(false);
    }

    public void tick() {
        super.tick();
        ++this.ticks;
        this.gipple.setAttacking(this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2);
    }
}
