package net.digitalpear.gipples_galore.common.entities.aneuploidian;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.renderer.base.GeoRenderState;

import java.util.Map;

public class AneuploidianEntityRenderState extends LivingEntityRenderState implements GeoRenderState {
    @Override
    public <D> void addGeckolibData(DataTicket<D> dataTicket, @Nullable D d) {

    }
    @Override
    public boolean hasGeckolibData(DataTicket<?> dataTicket) {
        return false;
    }

    @Override
    public <D> @Nullable D getGeckolibData(DataTicket<D> dataTicket) {
        return null;
    }

    @Override
    public Map<DataTicket<?>, Object> getDataMap() {
        return null;
    }
}
