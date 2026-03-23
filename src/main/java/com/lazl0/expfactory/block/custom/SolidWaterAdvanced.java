package com.lazl0.expfactory.block.custom;

import com.lazl0.expfactory.block.TooltipBlock;
import com.lazl0.expfactory.block.entity.SolidWaterAdvancedEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SolidWaterAdvanced extends TooltipBlock implements EntityBlock {
    public SolidWaterAdvanced(Properties properties, String key, String key2){
        super(properties, key, key2);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(stack.getItem() == Items.BUCKET){
            if(!level.isClientSide){
                ItemStack filled = new ItemStack(Items.WATER_BUCKET);
                if(!player.getAbilities().instabuild){//Survival Mode
                    stack.shrink(1);
                    if(!player.getInventory().add(filled)){
                        player.drop(filled,false);
                    }
                }else if(!player.getInventory().contains(filled)){
                    player.getInventory().add(filled);
                }
                level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1f, 1f);
            }
            return ItemInteractionResult.SUCCESS;
        }else if(stack.getItem() == Items.WATER_BUCKET){
            if(!level.isClientSide){
                ItemStack empty = new ItemStack(Items.BUCKET);
                if(!player.getAbilities().instabuild){
                    stack.shrink(1);
                    if(!player.getInventory().add(empty)){
                        player.drop(empty,false);
                    }
                }
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1f, 1f);
            }
            return ItemInteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SolidWaterAdvancedEntity(pos, state);
    }

}
