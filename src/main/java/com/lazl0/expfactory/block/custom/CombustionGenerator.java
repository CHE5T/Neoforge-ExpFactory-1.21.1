package com.lazl0.expfactory.block.custom;

import com.lazl0.expfactory.block.entity.CombustionGeneratorBlockEntity;
import com.lazl0.expfactory.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CombustionGenerator extends BaseEntityBlock {

    public CombustionGenerator(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CombustionGeneratorBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof CombustionGeneratorBlockEntity combustionGeneratorBlockEntity){
                combustionGeneratorBlockEntity.drops();
            }
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if(!level.isClientSide()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof CombustionGeneratorBlockEntity combustionGeneratorBlockEntity) {
                ((ServerPlayer) player).openMenu(new SimpleMenuProvider(combustionGeneratorBlockEntity, Component.translatable("block.exponential_factory.combustion_generator")), pos);
            }else{
                throw new IllegalStateException("Container Provider Missing.");
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide()){
            return null;
        }

        return createTickerHelper(blockEntityType, ModBlockEntities.COMBUSTION_GENERATOR_BE.get(),
                (level_1, blockPos, blockState, blockEntity) -> blockEntity.tick(level_1, blockPos, blockState));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        //Updates neighbors to be auto exported energy to
        if(!level.isClientSide()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof CombustionGeneratorBlockEntity combustionGeneratorBlockEntity) {
                combustionGeneratorBlockEntity.updateEnergyNeighbors();
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.exponential_factory.combustion_generator.shift_down"));
        }else{
            tooltipComponents.add(Component.translatable("tooltip.exponential_factory.combustion_generator"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
