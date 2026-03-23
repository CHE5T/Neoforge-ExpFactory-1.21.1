package com.lazl0.expfactory.block.entity;

import com.lazl0.expfactory.recipe.ModRecipes;
import com.lazl0.expfactory.recipe.SimpleMillRecipe;
import com.lazl0.expfactory.recipe.SimpleMillRecipeInput;
import com.lazl0.expfactory.registry.ModDataComponents;
import com.lazl0.expfactory.registry.energy.EnergyStorageExtra;
import com.lazl0.expfactory.screen.custom.SimpleMillMenu;
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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.lazl0.expfactory.Config.SIMPLE_MILL_DEFAULT_CAPACITY;

public class SimpleMillBlockEntity extends BlockEntity implements MenuProvider {
    private static final int CAPACITY = SIMPLE_MILL_DEFAULT_CAPACITY.getAsInt();
    private static final int MAX_INPUT = CAPACITY/20;

    private final EnergyStorageExtra energyStorage = new EnergyStorageExtra(CAPACITY, MAX_INPUT, 0){

    };
    //RF/tick used, going to be changed once I figure out how to add upgrades
    private final int energyConsumption = 20;

    public final ItemStackHandler inventory = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
        //Only allow recipe ingredients in INPUT_SLOT
        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if(slot==INPUT_SLOT){
                return isRecipeInput(stack);
            }
            return super.isItemValid(slot, stack);
        }
    };
    //Wrapper for inventory that is used in for automation (allowing/disallowing hopper slots)
    public final IItemHandler autoInventory = new CombinedInvWrapper(inventory){
        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if(slot!=INPUT_SLOT) return stack;
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if(slot!=OUTPUT_SLOT) return ItemStack.EMPTY;
            return super.extractItem(slot, amount, simulate);
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final ContainerData data;
    private int progress = 0;
    private int max_progress = 200;

    public SimpleMillBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SIMPLE_MILL_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i){
                    case 0 -> SimpleMillBlockEntity.this.progress;
                    case 1 -> SimpleMillBlockEntity.this.max_progress;
                    case 2 -> SimpleMillBlockEntity.this.energyStorage.getEnergyStored();
                    case 3 -> SimpleMillBlockEntity.this.energyStorage.getMaxEnergyStored();
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 0: SimpleMillBlockEntity.this.progress = i1;
                    case 1: SimpleMillBlockEntity.this.max_progress = i1;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.exponential_factory.simple_mill");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new SimpleMillMenu(containerId, playerInventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putInt("simple_mill.progress", progress);
        tag.putInt("simple_mill.max_progress", max_progress);
        //Serializes rf, should be moved to a blockEnergySerializer block, I wrote this a while ago and forgot what it means
        tag.put("energy", energyStorage.serializeNBT(registries));

        super.saveAdditional(tag, registries);
    }



    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("simple_mill.progress");
        max_progress = tag.getInt("simple_mill.max_progress");
        //Backwards compatible for old "simple mills" (remove after update)
        if(tag.contains("energy")){
            //Serializes rf
            this.energyStorage.deserializeNBT(registries, tag.get("energy"));
        }
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            //Pauses recipe if out of energy
            if(energyStorage.getEnergyStored()>=energyConsumption){
                //Increments the progress
                progress++;
                //Extracts the energy
                energyStorage.consumeEnergy(energyConsumption, false);


                setChanged(level, blockPos, blockState);
            }

            if(hasCraftingFinished()){
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void craftItem() {
        Optional<RecipeHolder<SimpleMillRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().outputItem();
        inventory.extractItem(INPUT_SLOT, 1, false);
        inventory.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(), inventory.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
        //inventory.insertItem(OUTPUT_SLOT, output, false);
    }

    private void resetProgress() {
        progress = 0;
        max_progress = 200;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.max_progress;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<SimpleMillRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }
        ItemStack output = recipe.get().value().outputItem();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }
    //Logic to make sure input items are only recipe items
    private boolean isRecipeInput(ItemStack stack) {
        return this.level.getRecipeManager().getRecipeFor(ModRecipes.SIMPLE_MILL_TYPE.get(), new SimpleMillRecipeInput(stack), this.level).isPresent();
    }

    private Optional<RecipeHolder<SimpleMillRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.SIMPLE_MILL_TYPE.get(), new SimpleMillRecipeInput(inventory.getStackInSlot(INPUT_SLOT)), level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.getStackInSlot(OUTPUT_SLOT).isEmpty() || inventory.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : inventory.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventory.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
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

    public final IEnergyStorage getEnergyStorage() {
        return energyStorage;
    }
}
