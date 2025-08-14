package com.lazl0.expfactory.block.entity;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ExpFactory.MODID);

    public static final Supplier<BlockEntityType<CapsuleEntity>> CAPSULE_BE =
            BLOCK_ENTITIES.register("capsule_be", () -> BlockEntityType.Builder.of(
                    CapsuleEntity::new, ModBlocks.CAPSULE.get()).build(null));

    public static final Supplier<BlockEntityType<SolidWaterAdvancedEntity>> SOLID_WATER_ADVANCED_BE =
            BLOCK_ENTITIES.register("solid_water_advanced_be", () -> BlockEntityType.Builder.of(
                    SolidWaterAdvancedEntity::new, ModBlocks.SOLID_WATER_ADVANCED.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
