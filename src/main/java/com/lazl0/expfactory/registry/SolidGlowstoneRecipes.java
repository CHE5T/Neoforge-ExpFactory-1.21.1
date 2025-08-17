package com.lazl0.expfactory.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;
import java.util.Map;

public final class SolidGlowstoneRecipes {
    private SolidGlowstoneRecipes() {}

    private static final Map<Fluid, Block> FLUID_TO_OUTPUT = new java.util.HashMap<>();

    static {
        // Defaults
        //FLUID_TO_OUTPUT.put(Fluids.FLOWING_WATER, Blocks.COBBLED_DEEPSLATE);
        FLUID_TO_OUTPUT.put(Fluids.FLOWING_LAVA, Blocks.END_STONE);

        // Examples for later:
        // FLUID_TO_OUTPUT.put(Fluids.WATER, Blocks.DEEPSLATE);
        // FLUID_TO_OUTPUT.put(Fluids.LAVA, Blocks.OBSIDIAN);
        // FLUID_TO_OUTPUT.put(ModFluids.MY_CUSTOM_FLOWING.get(), ModBlocks.MY_RESULT.get());
    }

    public static @Nullable Block getOutput(FluidState fs) {
        // Prefer exact match; then fall back to source vs flowing if you want finer control
        var exact = FLUID_TO_OUTPUT.get(fs.getType());
        if (exact != null) return exact;

        // Optional: generic water match
        //if (fs.is(Fluids.WATER)) return Blocks.DEEPSLATE; // example default
        return null;
    }
}