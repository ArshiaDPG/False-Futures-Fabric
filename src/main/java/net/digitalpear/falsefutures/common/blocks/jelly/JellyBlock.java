package net.digitalpear.falsefutures.common.blocks.jelly;

import net.digitalpear.falsefutures.FalseFuturesConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

public class JellyBlock extends Block {
    public static final BooleanProperty HALVED = BooleanProperty.of("halved");
    protected static final VoxelShape FULL_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape HALF_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);

    /*
        -Much of this block's code has been taken from slime blocks, I will comment on all custom methods describing what they do
     */
    public JellyBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HALVED, false));
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALVED);
    }

    /*
        -If half block, then destroy instead of push.
     */
    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return state.get(HALVED) ? PistonBehavior.DESTROY : PistonBehavior.NORMAL;
    }


    /*
        -Special effect for eating the jelly (is set in other classes.)
     */
    public void specialEffects(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){

    }
    /*
        -If the player is able to eat, eat half the block (Changes outline shape and piston behavior).
        -If half the block has already been eaten, then remove the block.
    */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getHungerManager().isNotFull() || player.isCreative()) {

            //Add food levels
            player.getHungerManager().add(FalseFuturesConfig.JELLY_FOOD_VALUE.get(), FalseFuturesConfig.JELLY_SATURATION_VALUE.get());

            world.playSound(player, pos, SoundEvents.ITEM_HONEY_BOTTLE_DRINK, SoundCategory.BLOCKS, 1.0f, 1.0f);
            player.swingHand(hand);

            if (state.get(HALVED)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
            }
            else{
                world.setBlockState(pos, this.getDefaultState().with(HALVED, true), 2);
            }
            if (FalseFuturesConfig.JELLY_SPECIAL_EFFECTS.get()) {
                specialEffects(state, world, pos, player, hand, hit);
            }
            return ActionResult.SUCCESS;
        }
        else {
            return ActionResult.PASS;
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HALVED) ? HALF_SHAPE : FULL_SHAPE;
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, state, pos, entity, fallDistance);
        } else {
            entity.handleFallDamage(fallDistance, 0.0F, DamageSource.FALL);
        }

    }



    /*
        Methods brought in from the slime block code
     */
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        } else {
            this.bounce(entity);
        }
    }
    private void bounce(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0D) {
            double d = entity instanceof LivingEntity ? 1.0D : 0.8D;
            entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
        }
    }
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getVelocity().y);
        if (d < 0.1D && !entity.bypassesSteppingEffects()) {
            double e = 0.4D + d * 0.2D;
            entity.setVelocity(entity.getVelocity().multiply(e, 1.0D, e));
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
