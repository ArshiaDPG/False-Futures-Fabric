package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.features.structures.GippleChurchStructure;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.structure.StructureType;

public class FFStructures {

    public static StructureType<GippleChurchStructure> GIPPLE_CHURCH = Registry.register(Registry.STRUCTURE_TYPE,
            new Identifier(FalseFutures.MOD_ID, "gipple_church"), () -> GippleChurchStructure.CODEC);

    public static void init() {
    }
}
