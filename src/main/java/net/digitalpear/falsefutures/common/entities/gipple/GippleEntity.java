package net.digitalpear.falsefutures.common.entities.gipple;

import net.digitalpear.falsefutures.FalseFuturesConfig;
import net.digitalpear.falsefutures.common.entities.something.SomethingEntity;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFItems;
import net.digitalpear.falsefutures.init.FFSoundEvents;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.digitalpear.falsefutures.init.tags.FFItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.event.EntityPositionSource;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.PositionSource;
import net.minecraft.world.event.listener.EntityGameEventHandler;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.Iterator;

public class GippleEntity extends PathAwareEntity implements Bucketable, IAnimatable, IAnimationTickable {
    /*
        General values
     */
    private static final TrackedData<Boolean> DIGESTING = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final float GENERIC_FLYING_SPEED = 0.25f;

    /*
        Interactions
     */
    private static final Ingredient GIPPLE_FOOD = Ingredient.fromTag(FFItemTags.GIPPLE_FOOD);
    private int digestingCooldown = 6000;
    private int pettingCooldown = 300;
    private int eatingCooldown;
    int eatingCooldownRange = 1000;
    private boolean isEating = false;
    int eatingAnimationCooldown = 30;

    /*
        Animations
     */
    private AnimationFactory factory = new AnimationFactory(this);

    /*
        Dancing
     */
    @Nullable
    private BlockPos jukeboxPos;
    private static final TrackedData<Boolean> DANCING = DataTracker.registerData(GippleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private final EntityGameEventHandler<GippleEntity.JukeboxEventListener> jukeboxEventHandler;


    /*
        Initialization
     */
    public GippleEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;

        this.moveControl = new FlightMoveControl(this, 1, true);
        PositionSource positionSource = new EntityPositionSource(this, this.getStandingEyeHeight());
        this.jukeboxEventHandler = new EntityGameEventHandler(new GippleEntity.JukeboxEventListener(positionSource, GameEvent.JUKEBOX_PLAY.getRange()));
        eatingCooldown = world.getRandom().nextBetween((int) (eatingCooldownRange * 0.8), eatingCooldownRange);
    }
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(1, new FleeEntityGoal(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(1, new FleeEntityGoal(this, WardenEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(1, new FleeEntityGoal(this, ZoglinEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(1, new GippleEntity.FlyOntoLichenGoal(this, 1.0D));
        this.goalSelector.add(2, new FlyRandomlyGoal(this));
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


    /*
    Data
    */
    public static DefaultAttributeContainer.Builder createGippleAttributes() {

        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, GENERIC_FLYING_SPEED)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0);
    }
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * (this.isBaby() ? 0.4f : 0.8f);
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
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DANCING, false);
        this.dataTracker.startTracking(DIGESTING, false);
        this.dataTracker.startTracking(FROM_BUCKET, false);
    }
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Digesting", this.isDigesting());
        nbt.putBoolean("FromBucket", this.isFromBucket());
        nbt.putBoolean("Dancing", this.isDancing());
    }
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setDigesting(nbt.getBoolean("Digesting"));
        this.setFromBucket(nbt.getBoolean("FromBucket"));
        this.setDancing(nbt.getBoolean("Dancing"));
    }

    public boolean isDigesting() {
        return dataTracker.get(DIGESTING);
    }
    public void setDigesting(boolean value) {
        dataTracker.set(DIGESTING, value);
    }
    public boolean isDancing() {
        return this.dataTracker.get(DANCING);
    }
    public void setDancing(boolean value) {
        this.dataTracker.set(DANCING, value);
    }


    /*
    Bucket stuff
    */
    @Override
    public boolean isFromBucket() {
        return dataTracker.get(FROM_BUCKET);
    }
    @Override
    public void setFromBucket(boolean fromBucket) {
        dataTracker.set(FROM_BUCKET, fromBucket);
    }
    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
    }
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
    }
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
    }
    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(FFItems.GIPPLE_BUCKET);
    }


    /*
        Tick stuff
     */

    public void tickMovement() {

        if (this.isDancing() && this.shouldStopDancing() && this.age % 20 == 0) {
            this.setDancing(false);
            this.jukeboxPos = null;
        }
        if (this.isEating){
            if (eatingAnimationCooldown <= 0){
                this.isEating = false;
            }
            else{
                eatingAnimationCooldown--;
            }
        }
        if (pettingCooldown > 0){
            pettingCooldown--;
        }
        if (!this.isBaby()) {
            eatLichen();
        }


        this.tickDigestionCooldown();

        super.tickMovement();
    }
    public void tickDigestionCooldown() {
        if (this.digestingCooldown > 0L) {
            --this.digestingCooldown;
        }

        if (!this.world.isClient() && this.digestingCooldown == 0L) {
            this.dataTracker.set(DIGESTING, false);
        }

    }

    /*
        Player Interaction
     */
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
            /*
                Go in bucket
             */
        if (stack.isOf(Items.WATER_BUCKET)) {
            player.swingHand(hand);
            Bucketable.tryBucket(player, hand, this);
        }
            /*
                Eat lichen and multiply
             */
        else if (GIPPLE_FOOD.test(stack) && this.isDigesting()) {
            mitosis();
            stack.decrement(1);
            return ActionResult.SUCCESS;
        }
            /*
                Pet the gipple
             */
        else if (FalseFuturesConfig.CAN_PET_GIPPLE.get() && pettingCooldown <= 0){
            this.world.playSound(player, this.getX(), this.getY(), this.getZ(), FFSoundEvents.ENTITY_GIPPLE_AMBIENT, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            double x = this.random.nextGaussian() * 0.02D;
            double y = this.random.nextGaussian() * 0.02D;
            double z = this.random.nextGaussian() * 0.02D;
            this.world.addParticle(ParticleTypes.HEART, this.getParticleX(1.0D), this.getRandomBodyY() + 0.5D, this.getParticleZ(1.0D), x, y, z);
            pettingCooldown = 60;
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
    public void mitosis(){
        //Chance to spawn a something instead of gipples
        int loop = 0;
        boolean spawnGippleNotSomething;
        if (FalseFuturesConfig.SOMETHING_SPAWNING_PERCENTAGE.get() == 0){
            spawnGippleNotSomething = false;
        }
        else{
            /*
                If something chance passes and hostile mobs can spawn and is not peaceful
             */
            spawnGippleNotSomething = (FalseFuturesConfig.SOMETHING_SPAWNING_PERCENTAGE.get() / 100 + (world.getDifficulty().getId() / 50)) > random.nextFloat()
                    && world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && world.getDifficulty() != Difficulty.PEACEFUL;
        }


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
        this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_BEEHIVE_EXIT, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        for (int particleLoop=0; particleLoop<=loop; particleLoop++){
            double x = this.random.nextGaussian() * 0.001D;
            double y = this.random.nextGaussian() * 0.06D;
            double z = this.random.nextGaussian() * 0.001D;
            this.world.addParticle(ParticleTypes.SOUL, this.getParticleX(1.0D), this.getRandomBodyY() + 0.5D, this.getParticleZ(1.0D), x, y, z);
        }
        this.discard();
    }
    public void spawnSomething(){
        SomethingEntity something = FFEntities.SOMETHING.create(world);
        if (this.isPersistent()) {
            something.setPersistent();
        }
        something.setCustomName(this.getCustomName());
        something.setAiDisabled(this.isAiDisabled());
        something.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.random.nextFloat() * 360.0F, 0.0F);
        world.spawnEntity(something);
    }
    /*
        i only determines rotation and relative z position, if that doesn't matter then just enter 0
     */
    public void spawnGipple(int i){
        GippleEntity gipple = FFEntities.GIPPLE.create(this.world);

        if (this.isPersistent()) {
            gipple.setPersistent();
        }
        gipple.setCustomName(this.getCustomName());
        gipple.setAiDisabled(this.isAiDisabled());
        gipple.setBaby(true);
        gipple.refreshPositionAndAngles(this.getX() + (double) i, this.getY() + 0.3D, this.getZ() + (double) i, this.random.nextFloat() * 360.0F, 0.0F);
        world.spawnEntity(gipple);
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
            Flying code
    */
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos).isIn(FFBlockTags.GIPPLE_FOOD) ? 10.0F : 0.0F;
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
                bl = blockState.getBlock() instanceof GlowLichenBlock || blockState.isIn(BlockTags.BASE_STONE_OVERWORLD) || blockState.isIn(FFBlockTags.GIPPLE_FOOD);
            } while(!bl || !this.mob.world.isAir(blockPos2) || !this.mob.world.isAir(mutable.set(blockPos2, Direction.UP)));

            return Vec3d.ofBottomCenter(blockPos2);
        }
    }

    public void eatLichen(){
        if (world.getBlockState(this.getBlockPos()).isIn(FFBlockTags.GIPPLE_FOOD) && !isDigesting()){
            if ((random.nextFloat() > 0.8) && eatingCooldown <= 0){
                world.playSound(null, this.getBlockPos(), FFSoundEvents.ENTITY_GIPPLE_BURP, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                this.world.syncWorldEvent(2001, this.getBlockPos(), Block.getRawIdFromState(Blocks.GLOW_LICHEN.getDefaultState()));
                isEating = true;
                eatingAnimationCooldown = 30;
                setDigesting(true);
                eatingCooldown = random.nextBetween((int) (eatingCooldownRange * 0.8), eatingCooldownRange);
            }
        }
    }



    /*
        Dancing code
    */
    private boolean shouldStopDancing() {
        return this.jukeboxPos == null || !this.jukeboxPos.isWithinDistance(this.getPos(), (double)GameEvent.JUKEBOX_PLAY.getRange()) || !this.world.getBlockState(this.jukeboxPos).isOf(Blocks.JUKEBOX);
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

    /*
        Animations
     */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isDancing()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("gipple.dance", true));

        }
        else if (this.isEating){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("gipple.eat", false));
        }
        else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("gipple.ambient", true));
        }
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return this.age;
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

        public boolean listen(ServerWorld world, GameEvent.Message event) {
            if (event.getEvent() == GameEvent.JUKEBOX_PLAY) {
                GippleEntity.this.updateJukeboxPos(new BlockPos(event.getEmitterPos()), true);
                return true;
            } else if (event.getEvent() == GameEvent.JUKEBOX_STOP_PLAY) {
                GippleEntity.this.updateJukeboxPos(new BlockPos(event.getEmitterPos()), false);
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

        this.updateLimbs(this, false);
    }
    private static class FlyRandomlyGoal extends Goal {
        private final GippleEntity gipple;

        public FlyRandomlyGoal(GippleEntity gipple) {
            this.gipple = gipple;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        public boolean canStart() {
            MoveControl moveControl = this.gipple.getMoveControl();
            if (!moveControl.isMoving()) {
                return true;
            } else {
                double d = moveControl.getTargetX() - this.gipple.getX();
                double e = moveControl.getTargetY() - this.gipple.getY();
                double f = moveControl.getTargetZ() - this.gipple.getZ();
                double g = d * d + e * e + f * f;
                return g < 1.0 || g > 3600.0;
            }
        }

        public boolean shouldContinue() {
            return false;
        }

        public void start() {
            Random random = this.gipple.getRandom();
            double d = this.gipple.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double e = this.gipple.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double f = this.gipple.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.gipple.getMoveControl().moveTo(d, e, f, GENERIC_FLYING_SPEED);
        }
    }
}
