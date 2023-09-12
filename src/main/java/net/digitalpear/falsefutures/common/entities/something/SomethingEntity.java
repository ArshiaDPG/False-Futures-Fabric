package net.digitalpear.falsefutures.common.entities.something;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SomethingEntity extends HostileEntity implements Monster, Flutterer, GeoEntity {
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlay("something.attack");
    protected static final RawAnimation AMBIENT_ANIM = RawAnimation.begin().thenLoop("something.ambient");
    private boolean isAttacking = false;


    public SomethingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.lookControl = new LookControl(this);
        this.moveControl = new FlightMoveControl(this, 20, true);
        this.experiencePoints = 20;
    }

    @Override
    public void tick() {
        isAttacking = this.getAttacking() != null && getLastAttackTime() < this.age + 80;
        super.tick();
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(1, new FlyGoal(this, 20.0D));
        this.goalSelector.add(1, new SomethingAttackGoal(this, 1.0D, true));
        this.targetSelector.add(1, new SomethingTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new SomethingTargetGoal<>(this, PathAwareEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createSomethingAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 36.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.5D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.4)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5);
    }
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.8F;
    }


    /*
        Flying code.
     */
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public boolean isInAir() {
        return !this.isOnGround();
    }

    /*
            Geckolib
         */

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "Walking", 3, this::setAnimations));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }

    public <E extends GeoAnimatable> PlayState setAnimations(final AnimationState<E> event) {
        if (isAttacking) {
            return event.setAndContinue(ATTACK_ANIM);
        } else {
            return event.setAndContinue(AMBIENT_ANIM);
        }
    }

    @Override
    public double getTick(Object object) {
        return this.age;
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
        ++this.ticks;
        this.gipple.setAttacking(this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2);
        super.tick();
    }
}
