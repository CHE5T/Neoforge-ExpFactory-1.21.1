package com.lazl0.expfactory.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class SolidWaterAdvancedEntity extends BlockEntity {
    public static final int CAPACITY = 1048576 * FluidType.BUCKET_VOLUME;

    private final FluidTank tank = new FluidTank(CAPACITY, fs -> fs.getFluid().isSame(Fluids.WATER)) {
        @Override
        public int fill(FluidStack resource, FluidAction action) {
            return 0; // cannot be filled
        }

        @Override
        public FluidStack drain(int maxDrain, FluidAction action) {
            return new FluidStack(Fluids.WATER, maxDrain); // infinite supply
        }
    };

    public SolidWaterAdvancedEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SOLID_WATER_ADVANCED_BE.get(), pos, state);
        tank.setFluid(new FluidStack(Fluids.WATER, CAPACITY));
    }

    // Expose a handler for capability registration
    public IFluidHandler getTank() {
        return tank;
    }
}
