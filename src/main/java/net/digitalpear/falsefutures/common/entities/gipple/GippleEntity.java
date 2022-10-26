package net.digitalpear.falsefutures.common.entities.gipple;

import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFSoundEvents;
import net.digitalpear.falsefutures.init.tags.FFItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Iterator;

public class GippleEntity extends AnimalEntity implements Flutterer, IAnimatable, IAnimationTickable {
    private static final TrackedData<Boolean> DIGESTING = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int digestingCooldown = 300;
    private float floatOnWaterDistance = 0.5f;
    private int blocksToCheckForWater = 10;
    private AnimationFactory factory = new AnimationFactory(this);


    public GippleEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
        this.moveControl = new GippleMoveControl(this, 20, true);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.FENCE, -1.0F);
    }

    @Override
    public void tick() {
        if (isDigesting()){
            if (digestingCooldown <= 0){
                digestingCooldown = 300;
                setDigesting(false);
            }
            else{
                digestingCooldown--;
            }
        }
        super.tick();
    }

    public void floatOnWater(){
        for (int i = 0; i <= blocksToCheckForWater; i++){
            if (world.getBlockState(this.getBlockPos().down(i)).isAir()) {
                if (world.getBlockState(this.getBlockPos().down(i + 1)).isOf(Blocks.WATER) || world.getBlockState(this.getBlockPos().down(i + 1)).isOf(Blocks.WATER_CAULDRON)) {

                    //Stay a distance from the water
                    if (this.getY() - this.getBlockPos().down(i + 1).getY() >= 1.5) {
                        this.setVelocity(this.getVelocity().x, -0.1, this.getVelocity().y);
                    }
                    else if (this.getY() - this.getBlockPos().down(i + 1).getY() < floatOnWaterDistance) {
                        this.setVelocity(this.getVelocity().x, 0.1, this.getVelocity().y);
                    }
                    else{
                        this.setVelocity(this.getVelocity().x, 0, this.getVelocity().y);
                    }
                    break;
                }
            }
        }
    }



    @Override
    public void tickMovement() {
        floatOnWater();
        super.tickMovement();
    }


    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(1, new FleeEntityGoal(this, WardenEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(1, new FleeEntityGoal(this, WardenEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(1, new FleeEntityGoal(this, ZoglinEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(2, new GippleEntity.FlyOntoLichenGoal(this, 1.0D));
        this.goalSelector.add(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    }

    public static DefaultAttributeContainer.Builder createGippleAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0);
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.8F;
    }

    public boolean isDigesting(){
        return dataTracker.get(DIGESTING);
    }
    public final void setDigesting(boolean bloated) {
        dataTracker.set(DIGESTING, bloated);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DIGESTING, false);
    }
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Digesting", this.isDigesting());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setDigesting(nbt.getBoolean("Digesting"));
    }
    /*
        Feed it
     */


    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isIn(FFItemTags.GIPPLE_FOOD)) {
            if (!this.world.isClient) {
                if (this.isDigesting()){
                    mitosis();
                }
                else{
                    this.setDigesting(true);
                }
            }
            stack.decrement(1);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    //Spawn two gipples with a rare chance of 3
    //5% chance to spawn a mega gipple
    public void mitosis(){
            int gippleNumber = random.nextFloat() > 0.9 ? 3 : 2;
            for (int i = 0; i <gippleNumber; i++) {
                if (random.nextFloat() < 0.9) {
                    GippleEntity gipples = FFEntities.GIPPLE.create(this.world);

                    if (this.isPersistent()) {
                        gipples.setPersistent();
                    }

                    gipples.setCustomName(this.getCustomName());
                    gipples.setAiDisabled(this.isAiDisabled());
                    gipples.refreshPositionAndAngles(this.getX() + (double) i, this.getY() + 0.5D, this.getZ() + (double) i, this.random.nextFloat() * 360.0F, 0.0F);
                    world.spawnEntity(gipples);
                } else {
//                    SomethingEntity gipples = Gipple.MEGA_GIPPLE.create(world);
//                    if (this.isPersistent()) {
//                        gipples.setPersistent();
//                    }
//                    gipples.setCustomName(this.getCustomName());
//                    gipples.setAiDisabled(this.isAiDisabled());
//                    gipples.refreshPositionAndAngles(this.getX() + (double) i, this.getY() + 0.7D, this.getZ() + (double) i, this.random.nextFloat() * 360.0F, 0.0F);
//                    world.spawnEntity(gipples);
                    explode();
                }
            }
            this.discard();
    }
    private void explode() {
        if (!this.world.isClient) {
            this.dead = true;
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 3f, Explosion.DestructionType.NONE);
            this.discard();
        }
    }

    /*
            Sounds
         */

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return FFSoundEvents.ENTITY_GIPPLE_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return FFSoundEvents.ENTITY_GIPPLE_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return FFSoundEvents.ENTITY_GIPPLE_HURT;
    }


    /*
        Flying code.
     */

    @Override
    public boolean hasNoGravity() {
        return !this.isSubmergedInWater();
    }

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

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }


    /*
        Geckolib
     */
    @Override
    public int tickTimer() {
        return this.age;
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gipple.swim", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<GippleEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /*
        Flying Goal
     */

    private static class FlyOntoLichenGoal extends FlyGoal {
        public FlyOntoLichenGoal(PathAwareEntity pathAwareEntity, double d) {
            super(pathAwareEntity, d);
        }

        @Nullable
        protected Vec3d getWanderTarget() {
            Vec3d vec3d = null;
            if (this.mob.isTouchingWater()) {
                vec3d = FuzzyTargeting.find(this.mob, 15, 15);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                vec3d = this.locateTree();
            }

            return vec3d == null ? super.getWanderTarget() : vec3d;
        }

        @Nullable
        private Vec3d locateTree() {
            BlockPos blockPos = this.mob.getBlockPos();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            BlockPos.Mutable mutable2 = new BlockPos.Mutable();
            Iterable<BlockPos> iterable = BlockPos.iterate(MathHelper.floor(this.mob.getX() - 3.0D), MathHelper.floor(this.mob.getY() - 6.0D), MathHelper.floor(this.mob.getZ() - 3.0D), MathHelper.floor(this.mob.getX() + 3.0D), MathHelper.floor(this.mob.getY() + 6.0D), MathHelper.floor(this.mob.getZ() + 3.0D));
            Iterator var5 = iterable.iterator();

            BlockPos blockPos2;
            boolean bl;
            do {
                do {
                    if (!var5.hasNext()) {
                        return null;
                    }

                    blockPos2 = (BlockPos)var5.next();
                } while(blockPos.equals(blockPos2));

                BlockState blockState = this.mob.world.getBlockState(mutable2.set(blockPos2, Direction.DOWN));
                bl = blockState.getBlock() instanceof GlowLichenBlock || blockState.isIn(BlockTags.BASE_STONE_OVERWORLD);
            } while(!bl || !this.mob.world.isAir(blockPos2) || !this.mob.world.isAir(mutable.set(blockPos2, Direction.UP)));

            return Vec3d.ofBottomCenter(blockPos2);
        }
    }

    public class GippleMoveControl extends MoveControl {
        private final int maxPitchChange;
        private final boolean noGravity;

        public GippleMoveControl(GippleEntity entity, int maxPitchChange, boolean noGravity) {
            super(entity);
            this.maxPitchChange = maxPitchChange;
            this.noGravity = noGravity;
        }
        public static boolean isOnWater(World world, Entity gipple) {
            for (int i = 0; i <= 10; i++) {
                if (world.getBlockState(gipple.getBlockPos().down(i)).isAir()) {
                    if (world.getBlockState(gipple.getBlockPos().down(i + 1)).isOf(Blocks.WATER) || world.getBlockState(gipple.getBlockPos().down(i + 1)).isOf(Blocks.WATER_CAULDRON)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void tick() {
            if (this.state == State.MOVE_TO) {
                this.state = State.WAIT;
                this.entity.setNoGravity(true);
                double x = this.targetX - this.entity.getX();
                double y = isOnWater(world, entity) ? 0 : this.targetY - this.entity.getY();
                double z = this.targetZ - this.entity.getZ();
                double g = x * x + y * y + z * z;
                if (g < 2.500000277905201E-7D) {
                    this.entity.setUpwardSpeed(0.0F);
                    this.entity.setForwardSpeed(0.0F);
                    return;
                }
                float flightPath = (float)(MathHelper.atan2(z, x) * 57.2957763671875D) - 90.0F;
                this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), flightPath, 90.0F));
                float speed;
                if (this.entity.isOnGround()) {
                    speed = (float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                } else {
                    speed = (float)(this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED));
                }

                this.entity.setMovementSpeed(speed);
                double j = Math.sqrt(x * x + z * z);
                if (Math.abs(y) > 9.999999747378752E-6D || Math.abs(j) > 9.999999747378752E-6D) {
                    float k = (float)(-(MathHelper.atan2(y, j) * 57.2957763671875D));
                    this.entity.setPitch(this.wrapDegrees(this.entity.getPitch(), k, (float)this.maxPitchChange));
                    this.entity.setUpwardSpeed(y > 0.0D ? speed : -speed);
                }
            } else {
                if (!this.noGravity) {
                    this.entity.setNoGravity(false);
                }

                this.entity.setUpwardSpeed(0.0F);
                this.entity.setForwardSpeed(0.0F);
            }

        }
    }

}
