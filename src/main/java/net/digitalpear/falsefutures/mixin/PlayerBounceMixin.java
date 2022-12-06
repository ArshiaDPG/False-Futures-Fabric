package net.digitalpear.falsefutures.mixin;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.jelly.JellyBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public abstract class PlayerBounceMixin {
    @Shadow public abstract Vec3d getVelocity();
    @Shadow public abstract void setVelocityClient(double x, double y, double z);
    public PlayerBounceMixin(EntityType<?> type, World world) {
    }

    @Inject(method = "collidesWithStateAtPos", at = @At("HEAD"))
    private void injectMethod(BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        Vec3d speed = this.getVelocity();
        double x;
        double z;
        if (state.getBlock() instanceof JellyBlock){
            if (speed.x > speed.z){
                x = -speed.x;
                z = speed.z;
            }
            else if (speed.z > speed.x){
                x = speed.x;
                z = -speed.z;
            }
            else{
                x = -speed.x;
                z = -speed.z;
            }
            this.setVelocityClient(x, this.getVelocity().y, z);
            FalseFutures.LOGGER.info("I have bounced.");
        }
        FalseFutures.LOGGER.info("I have not bounced, but collided.");
    }


}
