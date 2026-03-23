package com.lazl0.expfactory.registry;

import com.lazl0.expfactory.block.ModBlocks;
import com.lazl0.expfactory.block.entity.ModBlockEntities;
import com.lazl0.expfactory.item.ModItems;
import com.lazl0.expfactory.registry.energy.EnergyItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import static com.lazl0.expfactory.Config.*;

public final class ModCapabilities {

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {

        //Items Start
        event.registerItem(
                Capabilities.EnergyStorage.ITEM,
                (itemStack, context) -> new EnergyItem(itemStack, REDSTONE_BATTERY_DEFAULT_CAPACITY.getAsInt()),
                ModItems.REDSTONE_BATTERY.get()
        );
        //Items End



        //Blocks Start

        //Item Handlers Start
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.SIMPLE_MILL_BE.get(),
                (be, side) -> be.autoInventory
        );
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.COMBUSTION_GENERATOR_BE.get(),
                (be, side) -> be.inventory
        );
        //Item Handlers End

        

        //Fluids Start
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntities.SOLID_WATER_ADVANCED_BE.get(),
                (be, side) -> be.getTank()
        );
        //Fluids End



        //Energies Start
        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.THERMAL_BATTERY_BE.get(),
                (be, side) -> be.getEnergyStorage()
        );
        event.registerItem(
                Capabilities.EnergyStorage.ITEM,
                (itemStack, context) -> new EnergyItem(itemStack, THERMAL_BATTERY_DEFAULT_CAPACITY.getAsInt()),
                ModBlocks.THERMAL_BATTERY.asItem());


        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SIMPLE_MILL_BE.get(),
                (be, side) -> be.getEnergyStorage()
        );
        event.registerItem(
                Capabilities.EnergyStorage.ITEM,
                (itemStack, context) -> new EnergyItem(itemStack, SIMPLE_MILL_DEFAULT_CAPACITY.getAsInt()),
                ModBlocks.SIMPLE_MILL.asItem());

        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.COMBUSTION_GENERATOR_BE.get(),
                (be, side) -> be.getEnergyStorage()
        );
        event.registerItem(
                Capabilities.EnergyStorage.ITEM,
                (itemStack, context) -> new EnergyItem(itemStack, COMBUSTION_GENERATOR_DEFAULT_CAPACITY.getAsInt()),
                ModBlocks.COMBUSTION_GENERATOR.asItem());
        //Energies End

        //Blocks End
    }
}
