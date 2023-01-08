package net.digitalpear.falsefutures.common.blocks;

import com.google.common.collect.Maps;
import net.digitalpear.falsefutures.common.entities.gipple.GippleEntity;
import net.digitalpear.falsefutures.init.FFEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

public class GippleInfestedBlock  extends Block {
    private final Block regularBlock;
    public static final Map<Block, Block> REGULAR_TO_INFESTED_BLOCK = Maps.newIdentityHashMap();
    public static final Map<BlockState, BlockState> REGULAR_TO_INFESTED_STATE = Maps.newIdentityHashMap();
    public static final Map<BlockState, BlockState> INFESTED_TO_REGULAR_STATE = Maps.newIdentityHashMap();

    public GippleInfestedBlock(Block regularBlock, Settings settings) {
        super(settings.hardness(regularBlock.getHardness() / 2.0F).resistance(0.75F));
        this.regularBlock = regularBlock;
        REGULAR_TO_INFESTED_BLOCK.put(regularBlock, this);
    }

    public Block getRegularBlock() {
        return this.regularBlock;
    }

    public static boolean isInfestable(BlockState block) {
        return REGULAR_TO_INFESTED_BLOCK.containsKey(block.getBlock());
    }

    private void spawnGipple(ServerWorld world, BlockPos pos) {
        GippleEntity gipple = FFEntities.GIPPLE.create(world);
        gipple.refreshPositionAndAngles((double) pos.getX() + 0.5D, pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 0.0F, 0.0F);
        world.spawnEntity(gipple);
        gipple.playSpawnEffects();
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, stack, dropExperience);
        if (world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            this.spawnGipple(world, pos);
        }
    }

    public static BlockState fromRegularState(BlockState regularState) {
        return copyProperties(REGULAR_TO_INFESTED_STATE, regularState, () -> REGULAR_TO_INFESTED_BLOCK.get(regularState.getBlock()).getDefaultState());
    }

    public BlockState toRegularState(BlockState infestedState) {
        return copyProperties(INFESTED_TO_REGULAR_STATE, infestedState, () -> this.getRegularBlock().getDefaultState());
    }

    private static BlockState copyProperties(Map<BlockState, BlockState> stateMap, BlockState fromState, Supplier<BlockState> toStateSupplier) {
        return stateMap.computeIfAbsent(fromState, (infestedState) -> {
            BlockState blockState = toStateSupplier.get();

            Property property;
            for (Iterator var3 = infestedState.getProperties().iterator(); var3.hasNext(); blockState = blockState.contains(property) ? (BlockState) blockState.with(property, infestedState.get(property)) : blockState) {
                property = (Property) var3.next();
            }

            return blockState;
        });
    }
}