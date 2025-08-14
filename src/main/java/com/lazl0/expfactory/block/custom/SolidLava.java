package com.lazl0.expfactory.block.custom;

import com.lazl0.expfactory.registry.SolidLavaRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class SolidLava extends Block {
    public SolidLava(Properties props) { super(props); }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighbor,
                                  LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        FluidState fs = level.getFluidState(neighborPos);
        var outBlock = SolidLavaRecipes.getOutput(fs);
        if (outBlock != null) {
            level.setBlock(neighborPos, outBlock.defaultBlockState(), Block.UPDATE_ALL);
            level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1f, 1f);
            //Sometime I should add the minecraft smoke+fizz effect when cobblestone is made, or say it's a performance feature
        }
        return super.updateShape(state, dir, neighbor, level, pos, neighborPos);
    }

}