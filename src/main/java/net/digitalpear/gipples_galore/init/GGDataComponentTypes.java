package net.digitalpear.gipples_galore.init;

import com.mojang.serialization.Codec;
import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.UnaryOperator;

public class GGDataComponentTypes {
    public static final ComponentType<Boolean> LUMINOUS = register("luminous", (builder) -> builder.codec(Codec.BOOL).packetCodec(PacketCodecs.BOOLEAN).cache());


    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, GipplesGalore.id(id), (builderOperator.apply(ComponentType.builder())).build());
    }

    public static void init() {

    }
}
