package net.digitalpear.falsefutures.common.entities.gipple;

import net.digitalpear.falsefutures.common.entities.aneuploidian.AneuploidianEntity;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFItems;
import net.digitalpear.falsefutures.init.FFSoundEvents;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.digitalpear.falsefutures.init.tags.FFItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GippleEntity extends PassiveEntity implements Bucketable, GeoEntity, Flutterer {
    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_DANCING = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_LUMINOUS = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_EATING = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation DANCING_ANIM = RawAnimation.begin().thenLoop("gipple.dance");
    protected static final RawAnimation EATING_ANIM = RawAnimation.begin().thenPlayAndHold("gipple.eat");
    protected static final RawAnimation AMBIENT_ANIM = RawAnimation.begin().thenLoop("gipple.ambient");
    protected static final RawAnimation ON_GROUND_ANIM = RawAnimation.begin().thenLoop("gipple.floor");
    private static final Ingredient GIPPLE_FOOD = Ingredient.fromTag(FFItemTags.GIPPLE_FOOD);


    public GippleEntity(EntityType<? extends GippleEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
        this.moveControl = new FlightMoveControl(this, 10, true);

        /*PositionSource positionSource = new EntityPositionSource(this, this.getStandingEyeHeight());
        this.jukeboxEventHandler = new EntityGameEventHandler(new GippleEntity.JukeboxEventListener(positionSource, GameEvent.JUKEBOX_PLAY.getRange()));
        eatingCooldown = world.getRandom().nextBetween((int) (eatingCooldownRange * 0.8), eatingCooldownRange);

         */
    }

    public static DefaultAttributeContainer.Builder createGippleAttributes() {

        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.2);
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos).isAir() || world.getBlockState(pos).isIn(FFBlockTags.GIPPLE_FOOD) ? 10.0F : 0.0F;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(0, new FlyGoal(this, 1.0));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(0, new TemptGoal(this, 1.25, GIPPLE_FOOD, false));

    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }



    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return FFEntities.GIPPLE.create(world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(IS_DANCING, false);
        this.dataTracker.startTracking(IS_LUMINOUS, false);
        this.dataTracker.startTracking(FROM_BUCKET, false);
        this.dataTracker.startTracking(IS_EATING, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("FromBucket", this.isFromBucket());
        nbt.putBoolean("Dancing", this.isDancing());
        nbt.putBoolean("Eating", this.isEating());
        nbt.putBoolean("Luminous", this.isLuminous());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setFromBucket(nbt.getBoolean("FromBucket"));
        this.setDancing(nbt.getBoolean("Dancing"));
        this.setEating(nbt.getBoolean("Eating"));
        this.setLuminous(nbt.getBoolean("Luminous"));
    }


    //The following 4 methods were only to test the glowing and current splitting behavior. They will be changed
    /*
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemInHand = player.getStackInHand(hand);
        World world = this.getWorld();
        Random random = world.getRandom();
        if (isFood(itemInHand)) {
            if (!world.isClient()) {
                if (!this.isBaby()) {
                    if (!this.isLuminous()) {
                        this.setLuminous(true);
                    } else {
                        this.mitosis();
                    }
                } else {
                    this.growUp(AnimalEntity.toGrowUpAge(-getBreedingAge()), true);
                    return ActionResult.success(this.getWorld().isClient);
                }

                if (!player.getAbilities().creativeMode) {
                    itemInHand.decrement(1);
                }
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        }
        return super.interactMob(player, hand);
    }



    public void mitosis() {
        Random random = this.getRandom();
        World world = this.getWorld();
        int loop = 0;
        boolean spawnAneuploidianNotGipple = (0.1f + ((float) world.getDifficulty().getId() / 50)) > random.nextFloat() && world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && world.getDifficulty() != Difficulty.PEACEFUL;

        if (spawnAneuploidianNotGipple){
            //spawnSomething();
        } else {
            while (loop != 2) {
                spawnGipple(random);
                loop++;
            }
        }
        //doesn't work
        for (int particleLoop = 0; particleLoop <= 5; particleLoop++){
            double x = random.nextGaussian() * 0.001D;
            double y = random.nextGaussian() * 0.06D;
            double z = random.nextGaussian() * 0.001D;
            world.addParticle(ParticleTypes.COMPOSTER, this.getX() + 0.5D, this.getY() + 0.5D, getZ() + 0.5D, 0, 0, 0);
        }
        world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_BEEHIVE_EXIT, SoundCategory.NEUTRAL, 1.0f, 1.0f);

        this.discard();
    }
    public void spawnSomething(){
        AneuploidianEntity something = FFEntities.ANEUPLOIDIAN.create(getWorld());
        if (this.isPersistent()) {
            something.setPersistent();
        }
        something.setCustomName(this.getCustomName());
        something.setAiDisabled(this.isAiDisabled());
        something.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getRandom().nextFloat() * 360.0F, 0.0F);
        getWorld().spawnEntity(something);
    }

    public void spawnGipple(Random random) {
        GippleEntity gipple = FFEntities.GIPPLE.create(this.getWorld());
        if (gipple != null) {
            if (this.isPersistent()) {
                gipple.setPersistent();
            }
            NbtCompound nbt = this.writeNbt(new NbtCompound());
            nbt.remove("UUID");
            gipple.readNbt(nbt);
            gipple.setBaby(true);
            gipple.setLuminous(false);

            //Randomize position and rotation
            gipple.refreshPositionAndAngles(this.getX() + random.nextDouble(), this.getY() + random.nextDouble(), this.getZ() - random.nextDouble() , 0, 0);
            getWorld().spawnEntity(gipple);
        }

    }
            */

    

    public boolean isFood(ItemStack pStack) {
        return GIPPLE_FOOD.test(pStack);
    }



    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
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
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
    }

    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(FFItems.GIPPLE_BUCKET);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "GippleAnimations", 3, this::setAnimations));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return instanceCache;
    }

    public <E extends GeoAnimatable> PlayState setAnimations(final AnimationState<E> event) {
        if (this.isDancing()) {
            return event.setAndContinue(DANCING_ANIM);
        } else if (this.isEating()) {
            return event.setAndContinue(EATING_ANIM);
        } else if (this.isOnGround()) { //Is this working correctly?
            return event.setAndContinue(ON_GROUND_ANIM);
        } else {
            return event.setAndContinue(AMBIENT_ANIM);
        }

    }

    @Override
    public double getTick(Object object) {
        return this.age;
    }

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

    @Override
    public boolean isOnGround() {
        return this.getWorld().getBlockState(this.getBlockPos().down()).hasSolidTopSurface(this.getWorld(), this.getBlockPos().down(), this);
    }

    @Override
    public boolean isInAir() {
        return !this.isOnGround();
    }

/*Old code
    private static final TrackedData<Boolean> DIGESTING = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private static final float GENERIC_FLYING_SPEED = 0.1f;

    private int digestingCooldown = 6000;
    private int pettingCooldown = 300;
    private int eatingCooldown;
    int eatingCooldownRange = 200;
    int eatingAnimationCooldown = 30;

    @Nullable
    private BlockPos jukeboxPos;
    private final EntityGameEventHandler<GippleEntity.JukeboxEventListener> jukeboxEventHandler;

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, WardenEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, ZoglinEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(1, new GippleEntity.FlyOntoLichenGoal(this, 1.0D));
        this.goalSelector.add(1, new GippleEntity.FlyOntoWaterGoal(this, 1.0D));
        this.goalSelector.add(2, new TemptGoal(this, 1.2D, GIPPLE_FOOD, false));
        this.goalSelector.add(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if (spawnReason.equals(SpawnReason.BUCKET)){
            this.setPersistent();
            this.setFromBucket(true);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }




    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * (this.isBaby() ? 0.4f : 0.8f);
    }

    @Override
    public Box getBoundingBox(EntityPose pose) {
        if (this.isBaby()){
            return super.getBoundingBox(pose).contract(0.2);
        }
        else{
            return super.getBoundingBox(pose);
        }
    }

    @Override
    protected Box calculateBoundingBox() {
        if (this.isBaby()){
            return super.calculateBoundingBox().contract(0.5);
        }
        return super.calculateBoundingBox();
    }
    public Vec3d getLeashOffset() {
        return new Vec3d(0.0D, (double) this.getStandingEyeHeight() * 0.6D, (double) this.getWidth() * 0.1D);
    }


    public boolean isDigesting() {
        return dataTracker.get(DIGESTING);
    }
    public void setDigesting(boolean value) {
        dataTracker.set(DIGESTING, value);
    }

    public void tickMovement() {

        if (this.isDancing() && this.shouldStopDancing() && this.age % 20 == 0) {
            this.setDancing(false);
            this.jukeboxPos = null;
        }
        if (getIsEating()){
            if (eatingAnimationCooldown <= 0){
                setIsEating(false);
            }
            else{
                eatingAnimationCooldown--;
            }
        }
        if (pettingCooldown > 0){
            pettingCooldown--;
        }



        super.tickMovement();
    }
    public void tickDigestionCooldown() {
        if (this.digestingCooldown > 0L) {
            --this.digestingCooldown;
        }

        if (!this.getWorld().isClient() && this.digestingCooldown == 0L) {
            this.dataTracker.set(DIGESTING, false);
        }

    }

    @Override
    public void tick() {
        if (!this.isBaby()) {
            eatLichen();
        }
        this.tickDigestionCooldown();
        super.tick();
    }


    @Override
    public boolean damage(DamageSource source, float amount) {
        if (isDigesting()){
            int i = 1 + this.random.nextInt(3);

            for(int j = 0; j < i; ++j) {
                ItemEntity itemEntity = this.dropItem(FFItems.GELATIN, 1);
                if (itemEntity != null) {
                    itemEntity.setVelocity(itemEntity.getVelocity().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
                }
            }
            setDigesting(false);
        }
        return super.damage(source, amount);
    }
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
            //Go in bucket
        if (stack.isOf(Items.WATER_BUCKET)) {
            player.swingHand(hand);
            Bucketable.tryBucket(player, hand, this);
        }
         //       Eat lichen and multiply

        else if (GIPPLE_FOOD.test(stack) && this.isDigesting()) {
            mitosis();
            stack.decrement(1);
            return ActionResult.SUCCESS;
        }

                //Pet the gipple

        else if (pettingCooldown <= 0 && player.getStackInHand(hand).isEmpty()){
            this.getWorld().playSound(player, this.getX(), this.getY(), this.getZ(), FFSoundEvents.ENTITY_GIPPLE_AMBIENT, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            double x = this.random.nextGaussian() * 0.02D;
            double y = this.random.nextGaussian() * 0.02D;
            double z = this.random.nextGaussian() * 0.02D;
            this.getWorld().addParticle(ParticleTypes.HEART, this.getParticleX(1.0D), this.getRandomBodyY() + 0.5D, this.getParticleZ(1.0D), x, y, z);
            pettingCooldown = 60;
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
    public void mitosis(){
        //Chance to spawn a something instead of gipples
        int loop = 0;
        boolean spawnGippleNotSomething;

               // If something chance passes and hostile mobs can spawn and is not peaceful

            spawnGippleNotSomething = (0.1f + ((float) getWorld().getDifficulty().getId() / 50)) > random.nextFloat()
                    && getWorld().getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && getWorld().getDifficulty() != Difficulty.PEACEFUL;


        if (spawnGippleNotSomething){
            spawnSomething();
        }
        else{
            //Spawn two gipples with a rare chance of a third
            int gippleNumber = random.nextFloat() > 0.9 ? 3 : 2;
            while (loop != gippleNumber){
                spawnGipple(loop);
                loop++;
            }
        }
        this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_BEEHIVE_EXIT, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        for (int particleLoop=0; particleLoop<=loop; particleLoop++){
            double x = this.random.nextGaussian() * 0.001D;
            double y = this.random.nextGaussian() * 0.06D;
            double z = this.random.nextGaussian() * 0.001D;
            this.getWorld().addParticle(ParticleTypes.SOUL, this.getParticleX(1.0D), this.getRandomBodyY() + 0.5D, this.getParticleZ(1.0D), x, y, z);
        }
        this.discard();
    }
    public void spawnSomething(){
        SomethingEntity something = FFEntities.SOMETHING.create(getWorld());
        if (this.isPersistent()) {
            something.setPersistent();
        }
        something.setCustomName(this.getCustomName());
        something.setAiDisabled(this.isAiDisabled());
        something.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.random.nextFloat() * 360.0F, 0.0F);
        getWorld().spawnEntity(something);
    }

       // i only determines rotation and relative z position, if that doesn't matter then just enter 0

    public void spawnGipple(int i){
        GippleEntity gipple = FFEntities.GIPPLE.create(this.getWorld());

        if (this.isPersistent()) {
            gipple.setPersistent();
        }
        gipple.setCustomName(this.getCustomName());
        gipple.setAiDisabled(this.isAiDisabled());
        gipple.setBaby(true);
        gipple.refreshPositionAndAngles(this.getX() + (double) i, this.getY() + 0.3D, this.getZ() + (double) i, this.random.nextFloat() * 360.0F, 0.0F);
        getWorld().spawnEntity(gipple);
    }










           // Flying code



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
            Iterator<BlockPos> var5 = iterable.iterator();

            BlockPos blockPos2;
            boolean bl;
            do {
                do {
                    if (!var5.hasNext()) {
                        return null;
                    }

                    blockPos2 = var5.next();
                } while(blockPos.equals(blockPos2));

                BlockState blockState = this.mob.getWorld().getBlockState(mutable2.set(blockPos2, Direction.DOWN));
                bl = blockState.getBlock() instanceof GlowLichenBlock || blockState.isIn(BlockTags.BASE_STONE_OVERWORLD) || blockState.isIn(FFBlockTags.GIPPLE_FOOD);
            } while(!bl || !this.mob.getWorld().isAir(blockPos2) || !this.mob.getWorld().isAir(mutable.set(blockPos2, Direction.UP)));

            return Vec3d.ofBottomCenter(blockPos2).add(0, -0.3, 0);
        }
    }
    private static class FlyOntoWaterGoal extends FlyGoal {
        public FlyOntoWaterGoal(PathAwareEntity pathAwareEntity, double d) {
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
            Iterator<BlockPos> var5 = iterable.iterator();

            BlockPos blockPos2;
            boolean bl;
            do {
                do {
                    if (!var5.hasNext()) {
                        return null;
                    }

                    blockPos2 = var5.next();
                } while(blockPos.equals(blockPos2));

                BlockState blockState = this.mob.getWorld().getBlockState(mutable2.set(blockPos2, Direction.DOWN));
                bl = blockState.isOf(Blocks.WATER) || blockState.isOf(Blocks.WATER_CAULDRON);
            } while(!bl || !this.mob.getWorld().isAir(blockPos2) || !this.mob.getWorld().isAir(mutable.set(blockPos2, Direction.UP)));

            return Vec3d.ofBottomCenter(blockPos2);
        }
    }

    public void eatLichen(){
        if ((getWorld().getBlockState(this.getBlockPos()).isIn(FFBlockTags.GIPPLE_FOOD) || getWorld().getBlockState(this.getBlockPos().down()).isIn(FFBlockTags.GIPPLE_FOOD)) && !isDigesting() && eatingCooldown <= 0){
            getWorld().playSound(null, this.getBlockPos(), FFSoundEvents.ENTITY_GIPPLE_BURP, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            this.getWorld().syncWorldEvent(2001, this.getBlockPos(), Block.getRawIdFromState(Blocks.GLOW_LICHEN.getDefaultState()));
            setIsEating(true);
            eatingAnimationCooldown = 30;
            setDigesting(true);
            eatingCooldown = random.nextBetween((int) (eatingCooldownRange * 0.8), eatingCooldownRange);
        }
    }




       // Dancing code

    private boolean shouldStopDancing() {
        return this.jukeboxPos == null || !this.jukeboxPos.isWithinDistance(this.getPos(), GameEvent.JUKEBOX_PLAY.getRange()) || !this.getWorld().getBlockState(this.jukeboxPos).isOf(Blocks.JUKEBOX);
    }

    public void updateJukeboxPos(BlockPos jukeboxPos, boolean playing) {
        if (playing) {
            if (!this.isDancing()) {
                this.jukeboxPos = jukeboxPos;
                this.setDancing(true);
            }
        } else if (jukeboxPos.equals(this.jukeboxPos) || this.jukeboxPos == null) {
            this.jukeboxPos = null;
            this.setDancing(false);
        }

    }





    private class JukeboxEventListener implements GameEventListener {
        private final PositionSource positionSource;
        private final int range;

        public JukeboxEventListener(PositionSource positionSource, int range) {
            this.positionSource = positionSource;
            this.range = range;
        }

        public PositionSource getPositionSource() {
            return this.positionSource;
        }

        public int getRange() {
            return this.range;
        }

        @Override
        public boolean listen(ServerWorld world, GameEvent event, GameEvent.Emitter emitter, Vec3d emitterPos) {
            if (event == GameEvent.JUKEBOX_PLAY) {
                GippleEntity.this.updateJukeboxPos(new BlockPos((int)emitterPos.getX(), (int)emitterPos.getY(), (int)emitterPos.getZ()), true);
                return true;
            } else if (event == GameEvent.JUKEBOX_STOP_PLAY) {
                GippleEntity.this.updateJukeboxPos(new BlockPos((int)emitterPos.getX(), (int)emitterPos.getY(), (int)emitterPos.getZ()), false);
                return true;
            } else {
                return false;
            }
        }
    }

    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() || this.isLogicalSideForUpdatingMovement()) {
            if (this.isTouchingWater()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.800000011920929));
            } else if (this.isInLava()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.5));
            } else {
                this.updateVelocity(this.getMovementSpeed(), movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.9100000262260437));
            }
        }

        this.updateLimbs(false);
    }

    @Override
    public boolean canSpawn(WorldView world) {
        return world.getBlockState(this.getBlockPos().down()).isIn(FFBlockTags.GIPPLE_SPAWNABLES);
    }
 */
}
