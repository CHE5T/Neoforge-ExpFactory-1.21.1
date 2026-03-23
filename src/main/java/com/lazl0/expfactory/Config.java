package com.lazl0.expfactory;

import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue REDSTONE_BATTERY_DEFAULT_CAPACITY = BUILDER
            .comment("Redstone Battery Default Capacity")
            .defineInRange("redstoneBatteryDefaultCapacity", 65536, 1, Integer.MAX_VALUE);
    public static final ModConfigSpec.IntValue THERMAL_BATTERY_DEFAULT_CAPACITY = BUILDER
            .comment("Thermal Battery Default Capacity")
            .defineInRange("thermalBatteryDefaultCapacity", 2097152, 1, Integer.MAX_VALUE);
    public static final ModConfigSpec.IntValue SIMPLE_MILL_DEFAULT_CAPACITY = BUILDER
            .comment("Simple Mill Default Capacity")
            .defineInRange("simpleMillDefaultCapacity", 10240, 1, Integer.MAX_VALUE);
    public static final ModConfigSpec.IntValue COMBUSTION_GENERATOR_DEFAULT_CAPACITY = BUILDER
            .comment("Combustion Generator Default Capacity")
            .defineInRange("combustionGeneratorDefaultCapacity", 32768, 1, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();
}
