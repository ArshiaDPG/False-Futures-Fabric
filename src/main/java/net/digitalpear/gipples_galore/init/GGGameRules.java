package net.digitalpear.gipples_galore.init;


import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class GGGameRules {

    public static final GameRules.Key<GameRules.BooleanRule> SHOULD_APPLY_JELLY_EFFECTS =
            GameRuleRegistry.register("doJellyEffects", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));

    public static void init(){}

}
