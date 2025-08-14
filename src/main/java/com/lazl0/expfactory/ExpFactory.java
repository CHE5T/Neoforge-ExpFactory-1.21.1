package com.lazl0.expfactory;

import com.lazl0.expfactory.block.ModBlocks;
import com.lazl0.expfactory.block.entity.ModBlockEntities;
import com.lazl0.expfactory.datagen.DataGenerators;
import com.lazl0.expfactory.item.ModCreativeModeTabs;
import com.lazl0.expfactory.item.ModItems;
import com.lazl0.expfactory.registry.ModCapabilities;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ExpFactory.MODID)
public class ExpFactory {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "exponential_factory";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ExpFactory(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for mod loading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        //Register the ModCapabilities (for solid_water_advanced only as of now, unless I forget to change this)
        modEventBus.addListener(ModCapabilities::register);

        //Register the DataGenerator
        modEventBus.addListener(DataGenerators::gatherData);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        /*if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RAW_TINIUM);
            event.accept(ModItems.TINIUM_INGOT);
            event.accept(ModItems.TUENIUM_INGOT);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.RAW_TINIUM_BLOCK);
            event.accept(ModBlocks.TINIUM_BLOCK);
            event.accept(ModBlocks.TUENIUM_BLOCK);
            event.accept(ModBlocks.TINIUM_ORE);
        }*/
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
