package com.lazl0.expfactory.block.custom;

import com.lazl0.expfactory.block.entity.SolidWaterAdvancedEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SolidWaterAdvanced extends Block implements EntityBlock {
    public SolidWaterAdvanced(Properties properties){
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SolidWaterAdvancedEntity(pos, state);
    }

}
