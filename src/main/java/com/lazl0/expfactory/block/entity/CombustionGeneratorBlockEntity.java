package com.lazl0.expfactory.block.entity;

import com.lazl0.expfactory.registry.ModDataComponents;
import com.lazl0.expfactory.registry.energy.EnergyStorageExtra;
import com.lazl0.expfactory.screen.custom.CombustionGeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static com.lazl0.expfactory.Config.COMBUSTION_GENERATOR_DEFAULT_CAPACITY;

public class CombustionGeneratorBlockEntity extends BlockEntity implements MenuProvider {
    private static final int CAPACITY = COMBUSTION_GENERATOR_DEFAULT_CAPACITY.getAsInt();
    private static final int MAX_OUTPUT = CAPACITY/20;

    private final EnergyStorageExtra energyStorage = new EnergyStorageExtra(CAPACITY, 0, MAX_OUTPUT);

    public final ItemStackHandler inventory = new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return hasFuel(stack); //Checks if item is burnable
        }
    };

    protected final ContainerData data;
    private int burn_time = 0;
    private int max_burn_time = 0;
    //Info for energy exporting to neighbors
    private final ArrayList<IEnergyStorage> energyNeighbors = new ArrayList<>();
    private int exportPeriod = 0;

    public CombustionGeneratorBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COMBUSTION_GENERATOR_BE.get(), pos , blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i){
                    case 0 -> CombustionGeneratorBlockEntity.this.energyStorage.getEnergyStored();
                    case 1 -> CombustionGeneratorBlockEntity.this.energyStorage.getMaxEnergyStored();
                    case 2 -> CombustionGeneratorBlockEntity.this.burn_time;
                    case 3 -> CombustionGeneratorBlockEntity.this.max_burn_time;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 0: CombustionGeneratorBlockEntity.this.energyStorage.extractEnergy(i1, false);
                    case 1: CombustionGeneratorBlockEntity.this.energyStorage.receiveEnergy(i1, false);
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.put("energy", energyStorage.serializeNBT(registries));
        tag.putInt("combustion_generator.burn_time", burn_time);
        tag.putInt("combustion_generator.max_burn_time", max_burn_time);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        energyStorage.deserializeNBT(registries, tag.get("energy"));
        burn_time = tag.getInt("combustion_generator.burn_time");
        max_burn_time = tag.getInt("combustion_generator.max_burn_time");
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        //Generates energy when there is burnTime left and energy buffer isn't full
        if(burn_time > 0 && energyStorage.getEnergyStored() < energyStorage.getMaxEnergyStored()){
            energyStorage.generateEnergy(40, false);//Generates 40rf/t
            burn_time -= 10;//Subtracts by 10 since 1 is far too much rf generation(1 coal is 1600 ticks)
            setChanged(level, blockPos, blockState);
        }
        //Burns items when combustible is present
        if(!inventory.getStackInSlot(0).isEmpty()){
            if(burn_time <= 0){
                burn_time = inventory.getStackInSlot(0).getBurnTime(RecipeType.SMELTING);
                max_burn_time = burn_time;
                inventory.extractItem(0, 1, false);
            }
            setChanged(level, blockPos, blockState);
        }



        //Exports energy to neighbor blocks, checks neighbor blocks every 20 ticks
        if(exportPeriod++ >= 20){
            updateEnergyNeighbors();
            exportPeriod = 0;
        }
        exportEnergy(level, blockPos, blockState);
    }

    private void exportEnergy(Level level, BlockPos blockPos, BlockState blockState){
        //For every direction
        for(IEnergyStorage energyNeighbor : energyNeighbors){
            if(energyStorage.getEnergyStored() <= 0) break;
            int pushed = Math.min(energyStorage.extractEnergy(MAX_OUTPUT, true), energyNeighbor.receiveEnergy(MAX_OUTPUT, true));
            energyStorage.extractEnergy(pushed, false);
            energyNeighbor.receiveEnergy(pushed, false);
            setChanged(level, blockPos, blockState);
        }
    }
    //Public because it's called in neighborChanged method in the BaseEntityBlock
    public void updateEnergyNeighbors(){
        energyNeighbors.clear();
        for(Direction side : Direction.values()){
            //Check for neighbor's energy capability
            IEnergyStorage neighborEnergy = level.getCapability(Capabilities.EnergyStorage.BLOCK, worldPosition.relative(side), side.getOpposite());
            if(neighborEnergy != null && neighborEnergy.canReceive()){
                energyNeighbors.add(neighborEnergy);
            }
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.exponential_factory.combustion_generator");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CombustionGeneratorMenu(containerId, playerInventory, this, this.data);
    }

    private boolean hasFuel(ItemStack stack){
        return stack.getBurnTime(RecipeType.SMELTING) > 0;
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    //Stores the blocks energy as item component
    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(ModDataComponents.ENERGY.get(), this.energyStorage.getEnergyStored());
    }
    //Pulls the blocks energy from item component
    @Override
    protected void applyImplicitComponents(DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.energyStorage.setEnergyStored(componentInput.getOrDefault(ModDataComponents.ENERGY.get(), 0));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries){
        return saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public IEnergyStorage getEnergyStorage() {
        return energyStorage;
    }
}
