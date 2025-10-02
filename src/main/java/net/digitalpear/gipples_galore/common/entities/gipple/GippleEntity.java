package net.digitalpear.gipples_galore.common.entities.gipple;

import net.digitalpear.gipples_galore.common.entities.aneuploidian.AneuploidianEntity;
import net.digitalpear.gipples_galore.init.*;
import net.digitalpear.gipples_galore.init.tags.GGBlockTags;
import net.digitalpear.gipples_galore.init.tags.GGItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.conversion.EntityConversionContext;
import net.minecraft.entity.conversion.EntityConversionType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.manager.AnimatableManager;
import software.bernie.geckolib.animatable.processing.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class GippleEntity extends PassiveEntity implements Bucketable, GeoEntity, Flutterer {
    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_DANCING = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_LUMINOUS = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_EATING = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> HUNGRY_COUNTDOWN = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> EATING_TIMER = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> PLACE_GELATIN_TIMER = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation DANCING_ANIM = RawAnimation.begin().thenLoop("gipple.dance");
    protected static final RawAnimation EATING_ANIM = RawAnimation.begin().thenPlay("gipple.eat");
    protected static final RawAnimation AMBIENT_ANIM = RawAnimation.begin().thenLoop("gipple.ambient");
    protected static final RawAnimation ON_GROUND_ANIM = RawAnimation.begin().thenLoop("gipple.floor");

    public static float INFLATED_SCALE = 1.3f;
    public static float BABY_SCALE = 0.8f;

    public GippleEntity(EntityType<? extends GippleEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
        this.moveControl = new FlightMoveControl(this, 10, true);
    }

    public static DefaultAttributeContainer.Builder createGippleAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.TEMPT_RANGE, 10)
                .add(EntityAttributes.MAX_HEALTH, 6.0D)
                .add(EntityAttributes.FLYING_SPEED, 0.4)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.2);
    }


    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos).isAir() || world.getBlockState(pos).isIn(GGBlockTags.GIPPLE_FOOD) ? 10.0F : 0.0F;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new FindAndEatFoodGoal(this, 1.2, 10, 5));
        this.goalSelector.add(0, new FindBlockAndPlaceGelatinGoal(this, 1.2, 10, 2));
        this.goalSelector.add(2, new GippleFlyAroundGoal());
        this.goalSelector.add(3, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(1, new GippleTemptGoal(this, GGItemTags.GIPPLE_FOOD));
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanOpenDoors(false);
        birdNavigation.setCanSwim(false);
        return birdNavigation;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return GGEntityTypes.GIPPLE.create(world, SpawnReason.BREEDING);
    }


    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(IS_DANCING, false);
        builder.add(IS_LUMINOUS, false);
        builder.add(FROM_BUCKET, false);
        builder.add(IS_EATING, false);
        builder.add(HUNGRY_COUNTDOWN, genRandomHungryCountdown());
        builder.add(EATING_TIMER, 40);
        builder.add(PLACE_GELATIN_TIMER, 300);
    }


    @Override
    public void writeCustomData(WriteView nbt) {
        super.writeCustomData(nbt);
        nbt.putBoolean("FromBucket", this.isFromBucket());
        nbt.putBoolean("Dancing", this.isDancing());
        nbt.putBoolean("Eating", this.isEating());
        nbt.putBoolean("Luminous", this.isLuminous());
        nbt.putInt("hungryCountdown", this.getHungryCountdown());
        nbt.putInt("eatingTimer", this.getEatingTimer());
        nbt.putInt("placeGelatinTimer", this.getPlaceGelatinTimer());
    }

    @Override
    public void readCustomData(ReadView nbt) {
        super.readCustomData(nbt);
        this.setFromBucket(nbt.getBoolean("FromBucket", false));
        this.setDancing(nbt.getBoolean("Dancing", false));
        this.setEating(nbt.getBoolean("Eating", false));
        this.setLuminous(nbt.getBoolean("Luminous", false));
        this.setHungryCountdown(nbt.getInt("hungryCountdown", 0));
        this.setEatingTimer(nbt.getInt("eatingTimer", 0));
        this.setPlaceGelatinTimer(nbt.getInt("placeGelatinTimer", 0));
    }

    @Override
    public void tick() {
        super.tick();
        if (getHungryCountdown() != 0 && !this.isLuminous() && !this.isBaby()) {
            setHungryCountdown(getHungryCountdown() - 1);
        }
        if (getEatingTimer() != 0 && !this.isLuminous() && this.isEating()) {
            setEatingTimer(getEatingTimer() - 1);
        }
        if (getPlaceGelatinTimer() != 0 && this.isLuminous()) {
            setPlaceGelatinTimer(getPlaceGelatinTimer() - 1);
        }
    }

    public int genRandomHungryCountdown() {
        return this.random.nextBetween(400, 1000);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemInHand = player.getStackInHand(hand);
        if (itemInHand.isOf(Items.WATER_BUCKET)) {
            player.swingHand(hand);
            detachLeash();
            Bucketable.tryBucket(player, hand, this);
        } else if (itemInHand.isIn(GGItemTags.GIPPLE_FOOD)) {
            if (!this.isBaby()) {
                if (!this.isLuminous()) {
                    if (this.getHungryCountdown() == 0) {
                        this.setLuminous(true);
                        this.setHungryCountdown(genRandomHungryCountdown());
                    }
                    return ActionResult.PASS;
                } else {
                    mitosis();
                }
            } else {
                this.growUp(AnimalEntity.toGrowUpAge(-getBreedingAge()), true);
                return ActionResult.SUCCESS_SERVER.noIncrementStat();
            }

            if (!player.getAbilities().creativeMode) {
                itemInHand.decrement(1);
            }
            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }

    public void mitosis() {
        Random random = this.getRandom();
        World world = this.getWorld();
        if (world instanceof ServerWorld serverWorld){
            boolean spawnAneuploidianNotGipple = (serverWorld.getGameRules().getInt(GGGameRules.GIPPLE_MUTATION) * 0.01) + ((float) world.getDifficulty().getId() / 50) > random.nextFloat() && world.getDifficulty() != Difficulty.PEACEFUL;

            if (spawnAneuploidianNotGipple){
                spawnAneuploidian();
            } else {
                this.detachLeash();
                int gippleNumber = 2;
                if (random.nextFloat() > 0.9){
                    gippleNumber++;
                }
                for (int i = 0; i < gippleNumber; i++){
                    spawnGipple(random);
                }
            }
        }



        for (int particleLoop = 0; particleLoop <= 5; particleLoop++){
            double x = random.nextGaussian() * 0.001D;
            double y = random.nextGaussian() * 0.06D;
            double z = random.nextGaussian() * 0.001D;
            world.addParticleClient(ParticleTypes.COMPOSTER, this.getX() + 0.5D, this.getY() + 0.5D, getZ() + 0.5D, x, y, z);
        }
        world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_BEEHIVE_EXIT, SoundCategory.NEUTRAL, 1.0f, 1.0f);

        this.discard();
    }
    public void spawnAneuploidian(){
        this.convertTo(GGEntityTypes.ANEUPLOIDIAN, EntityConversionContext.create(this, false, true), MobEntity::setPersistent);
    }

    public void spawnGipple(Random random) {
        GippleEntity gipple = GGEntityTypes.GIPPLE.create(this.getWorld(), SpawnReason.CONVERSION);
        if (gipple != null) {
            gipple.setPersistent();
            gipple.copyComponentsFrom(this);
            gipple.setBaby(true);
            gipple.setLuminous(false);
            gipple.refreshPositionAndAngles(this.getX() + random.nextDouble(), this.getY() + random.nextDouble(), this.getZ() - random.nextDouble() , 0, 0);
            getWorld().spawnEntity(gipple);
        }

    }

    @Override
    public boolean handleFallDamage(double fallDistance, float damagePerDistance, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    public boolean isDancing() {
        return this.dataTracker.get(IS_DANCING);
    }
    public void setDancing(boolean value) {
        this.dataTracker.set(IS_DANCING, value);
    }

    public boolean isEating() {
        return this.dataTracker.get(IS_EATING);
    }
    public void setEating(boolean value) {
        this.dataTracker.set(IS_EATING, value);
    }

    public boolean isLuminous() {
        return this.dataTracker.get(IS_LUMINOUS);
    }
    public void setLuminous(boolean value) {
        this.dataTracker.set(IS_LUMINOUS, value);
    }

    public int getHungryCountdown() {
        return this.dataTracker.get(HUNGRY_COUNTDOWN);
    }
    public void setHungryCountdown(int value) {
        this.dataTracker.set(HUNGRY_COUNTDOWN, value);
    }

    public int getEatingTimer() {
        return this.dataTracker.get(EATING_TIMER);
    }
    public void setEatingTimer(int value) {
        this.dataTracker.set(EATING_TIMER, value);
    }

    public int getPlaceGelatinTimer() {
        return this.dataTracker.get(PLACE_GELATIN_TIMER);
    }
    public void setPlaceGelatinTimer(int value) {
        this.dataTracker.set(PLACE_GELATIN_TIMER, value);
    }

    @Override
    public boolean isFromBucket() {
        return dataTracker.get(FROM_BUCKET);
    }
    @Override
    public void setFromBucket(boolean fromBucket) {
        dataTracker.set(FROM_BUCKET, fromBucket);
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, nbtCompound -> {
            if (this.isAiDisabled()) {
                nbtCompound.putBoolean("NoAI", this.isAiDisabled());
            }

            if (this.isSilent()) {
                nbtCompound.putBoolean("Silent", this.isSilent());
            }

            if (this.hasNoGravity()) {
                nbtCompound.putBoolean("NoGravity", this.hasNoGravity());
            }

            if (this.isInvulnerable()) {
                nbtCompound.putBoolean("Invulnerable", this.isInvulnerable());
            }
            nbtCompound.putFloat("Health", this.getHealth());

            /*
                Custom values
             */
            nbtCompound.putInt("Age", this.getBreedingAge());
            nbtCompound.putBoolean("Luminous", this.isLuminous());
            nbtCompound.putInt("HungryCountdown", this.getHungryCountdown());
            nbtCompound.putInt("EatingTimer", this.getEatingTimer());
            nbtCompound.putInt("PlaceGelatinTimer", this.getPlaceGelatinTimer());
        });
    }

    @Override
    public float getScaleFactor() {
        if (isLuminous()){
            return INFLATED_SCALE;
        }
        return this.isBaby() ? BABY_SCALE : super.getScaleFactor();
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        nbt.getInt("Age").ifPresent(this::setBreedingAge);
        nbt.getInt("HungryCountdown").ifPresent(this::setHungryCountdown);
        nbt.getInt("PlaceGelatinTimer").ifPresent(this::setPlaceGelatinTimer);
        nbt.getInt("EatingTimer").ifPresent(this::setEatingTimer);
        nbt.getBoolean("Luminous").ifPresent(this::setLuminous);
        this.setFromBucket(true);
        Bucketable.copyDataFromNbt(this, nbt);
    }

    @Override
    protected void copyComponentsFrom(ComponentsAccess from) {
        super.copyComponentsFrom(from);
        this.copyComponentFrom(from, GGDataComponentTypes.LUMINOUS);
    }

    @Override
    protected <T> boolean setApplicableComponent(ComponentType<T> type, T value) {
        if (type == GGDataComponentTypes.LUMINOUS){
            this.setLuminous(castComponentValue(GGDataComponentTypes.LUMINOUS,value));
            return true;
        }
        return super.setApplicableComponent(type, value);
    }

    @Nullable
    @Override
    public <T> T get(ComponentType<? extends T> type) {
        return type == GGDataComponentTypes.LUMINOUS ? castComponentValue(type, this.isLuminous()) : super.get(type);
    }

    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(GGItems.GIPPLE_BUCKET);
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return effect.getEffectType() != GGStatusEffects.GIPPLE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GippleEntity>(animationTest -> {
            if (animationTest.animatable().isDancing()){
                animationTest.setAndContinue(DANCING_ANIM);
            }else if (this.isEating()) {
                return animationTest.setAndContinue(EATING_ANIM);
            } else if (this.isOnGround()) {
                return animationTest.setAndContinue(ON_GROUND_ANIM);
            } else {
                return animationTest.setAndContinue(AMBIENT_ANIM);
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }


    @Override
    public double getTick(Object object) {
        return this.age;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return GGSoundEvents.ENTITY_GIPPLE_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return GGSoundEvents.ENTITY_GIPPLE_AMBIENT.value();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return GGSoundEvents.ENTITY_GIPPLE_HURT;
    }

    @Override
    public boolean isOnGround() {
        return this.getWorld().getBlockState(this.getBlockPos().down()).hasSolidTopSurface(this.getWorld(), this.getBlockPos().down(), this);
    }

    @Override
    public boolean isInAir() {
        return !this.isOnGround();
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        if (spawnReason.equals(SpawnReason.BUCKET)){
            this.setPersistent();
            this.setFromBucket(true);
        }
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public Vec3d getLeashOffset() {
        return new Vec3d(0.0D, (double) this.getStandingEyeHeight() * 0.6D, (double) this.getWidth() * 0.1D);
    }

    public static class FindAndEatFoodGoal extends MoveToTargetPosGoal {
        public final GippleEntity mob;

        public FindAndEatFoodGoal(GippleEntity mob, double speed, int range, int maxYDifference) {
            super(mob, speed, range, maxYDifference);
            this.mob = mob;
        }

        @Override
        public boolean canStart() {
            return mob.getHungryCountdown() == 0 && !mob.isLuminous() && !this.mob.isBaby() && super.canStart();
        }

        @Override
        protected BlockPos getTargetPos() {
            return this.targetPos;
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.getBlockState(pos).isIn(GGBlockTags.GIPPLE_FOOD);
        }

        @Override
        public boolean shouldContinue() {
            return !mob.isLuminous() && super.shouldContinue();
        }

        @Override
        public void tick() {
            if (this.hasReached()) {
                mob.setEating(true);

                if (mob.getEatingTimer() == 0) {
                    if (mob.random.nextBetween(0, 3) == 0) {
                        mob.getWorld().breakBlock(getTargetPos(), false);
                    }
                    if (mob.getRandom().nextFloat() < 0.5){
                        mob.playSound(GGSoundEvents.ENTITY_GIPPLE_BURP);
                    }
                    mob.setEating(false);
                    mob.setLuminous(true);
                }
            } else {
                mob.setEating(false);
            }
            super.tick();
        }

        @Override
        public void stop() {
            mob.setEatingTimer(40);
            mob.setHungryCountdown(mob.genRandomHungryCountdown());
            super.stop();
        }
    }


    public static class FindBlockAndPlaceGelatinGoal extends MoveToTargetPosGoal {
        public final GippleEntity mob;

        public FindBlockAndPlaceGelatinGoal(GippleEntity mob, double speed, int range, int maxYDifference) {
            super(mob, speed, range, maxYDifference);
            this.mob = mob;
        }

        @Override
        public boolean canStart() {
            return mob.getPlaceGelatinTimer() == 0 && mob.isLuminous() && !this.mob.isBaby() && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return mob.isLuminous() && super.shouldContinue();
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.getBlockState(pos).isIn(BlockTags.BASE_STONE_OVERWORLD) && world.getBlockState(pos.up()).isAir();
        }

        @Override
        public void tick() {
            if (this.hasReached()) {
                mob.getWorld().setBlockState(this.targetPos.up(), GGBlocks.GELATIN_LAYER.getDefaultState());
                mob.setLuminous(false);
            }
            super.tick();
        }

        @Override
        public void stop() {
            super.stop();
            mob.setPlaceGelatinTimer(300);
        }
    }

    class GippleFlyAroundGoal extends Goal {
        public final GippleEntity mob;

        GippleFlyAroundGoal() {
            this.setControls(EnumSet.of(Goal.Control.MOVE));
            this.mob = GippleEntity.this;
        }

        @Override
        public boolean canStart() {
            return mob.navigation.isIdle() && mob.random.nextInt(10) == 0;
        }

        @Override
        public boolean shouldContinue() {
            return mob.navigation.isFollowingPath();
        }

        @Override
        public void start() {
            Vec3d vec3d = this.getRandomLocation();
            if (vec3d != null) {
                mob.navigation.startMovingAlong(mob.navigation.findPathTo(BlockPos.ofFloored(vec3d), 1), 1.0);
            }
        }

        @Nullable
        private Vec3d getRandomLocation() {
            Vec3d vec3d2 = mob.getRotationVec(0.0f);
            Vec3d vec3d3 = AboveGroundTargeting.find(mob, 8, 7, vec3d2.x, vec3d2.z, 1.5707964f, 3, 1);
            if (vec3d3 != null) {
                return vec3d3;
            }
            return NoPenaltySolidTargeting.find(mob, 8, 4, -2, vec3d2.x, vec3d2.z, 1.5707963705062866);
        }
    }

    static class GippleTemptGoal extends TemptGoal {
        public final GippleEntity mob;

        public GippleTemptGoal(GippleEntity mob, TagKey<Item> tagKey) {
            super(mob, 1.25, stack -> stack.isIn(tagKey), false);
            this.mob = mob;
        }

        @Override
        public boolean canStart() {
            return mob.getHungryCountdown() == 0 && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return mob.getHungryCountdown() == 0 && super.shouldContinue();
        }
    }
}
