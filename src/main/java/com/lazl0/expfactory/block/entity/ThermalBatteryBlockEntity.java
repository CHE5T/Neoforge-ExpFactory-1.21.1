package com.lazl0.expfactory.block.entity;

import com.lazl0.expfactory.registry.ModDataComponents;
import com.lazl0.expfactory.registry.energy.EnergyStorageExtra;
import com.lazl0.expfactory.screen.custom.ThermalBatteryMenu;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import static com.lazl0.expfactory.Config.THERMAL_BATTERY_DEFAULT_CAPACITY;

public class ThermalBatteryBlockEntity extends BlockEntity implements MenuProvider {
    private static final int CAPACITY = THERMAL_BATTERY_DEFAULT_CAPACITY.getAsInt();
    private static final int MAX_IO = CAPACITY/20;

    private final EnergyStorageExtra energyStorage = new EnergyStorageExtra(CAPACITY, MAX_IO);
    private int itemInputTransferRate;
    private int itemOutputTransferRate;

    public final ItemStackHandler inventory = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
            if(hasEnergyCapability(inventory.getStackInSlot(0))) itemInputTransferRate = inventory.getStackInSlot(0).getCapability(Capabilities.EnergyStorage.ITEM).getMaxEnergyStored() / 20;
            if(hasEnergyCapability(inventory.getStackInSlot(1))) itemOutputTransferRate = inventory.getStackInSlot(1).getCapability(Capabilities.EnergyStorage.ITEM).getMaxEnergyStored() / 20;
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return hasEnergyCapability(stack);
            //return super.isItemValid(slot, stack);
        }
    };

    protected final ContainerData data;

    public ThermalBatteryBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.THERMAL_BATTERY_BE.get(), pos , blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i){
                    case 0 -> ThermalBatteryBlockEntity.this.energyStorage.getEnergyStored();
                    case 1 -> ThermalBatteryBlockEntity.this.energyStorage.getMaxEnergyStored();
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 0: ThermalBatteryBlockEntity.this.energyStorage.extractEnergy(i1, false);
                    case 1: ThermalBatteryBlockEntity.this.energyStorage.receiveEnergy(i1, false);
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.put("energy", energyStorage.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        this.energyStorage.deserializeNBT(registries, tag.get("energy"));
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        //Extracts energy from the item when there is an item present
        if(!inventory.getStackInSlot(0).isEmpty()){
            IEnergyStorage extractItem = inventory.getStackInSlot(0).getCapability(Capabilities.EnergyStorage.ITEM);
            //int transferRate = extractItem.getMaxEnergyStored()/20;
            int consumed = Math.min(energyStorage.receiveEnergy(itemInputTransferRate, true), extractItem.extractEnergy(itemInputTransferRate, true));
            energyStorage.receiveEnergy(consumed, false);
            extractItem.extractEnergy(consumed, false);

            setChanged(level, blockPos, blockState);
        }
        //Inserts energy into the item when there is an item present
        if (!inventory.getStackInSlot(1).isEmpty()) {
            IEnergyStorage recieveItem = inventory.getStackInSlot(1).getCapability(Capabilities.EnergyStorage.ITEM);
            //only used the minimum energy of the two accepted
            int consumed = Math.min(energyStorage.extractEnergy(itemOutputTransferRate, true), recieveItem.receiveEnergy(itemOutputTransferRate, true));
            energyStorage.extractEnergy(consumed, false);
            recieveItem.receiveEnergy(consumed, false);
            setChanged(level, blockPos, blockState);
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.exponential_factory.thermal_battery");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ThermalBatteryMenu(containerId, playerInventory, this, this.data);
    }

    private boolean hasEnergyCapability(ItemStack stack){
        return stack.getCapability(Capabilities.EnergyStorage.ITEM) != null;
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
