package com.lazl0.expfactory.registry;

import com.lazl0.expfactory.block.entity.ModBlockEntities;
import com.lazl0.expfactory.block.entity.SolidWaterAdvancedEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public final class ModCapabilities {
    private ModCapabilities() {}

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntities.SOLID_WATER_ADVANCED_BE.get(),
                (be, side) -> be.getTank()
        );
    }

}
