package com.lazl0.expfactory.datagen;

import com.lazl0.expfactory.block.ModBlocks;
import com.lazl0.expfactory.block.custom.SolidWater;
import com.lazl0.expfactory.item.ModItems;
import com.lazl0.expfactory.registry.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.common.Mod;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        //Makes the generated/resources/data/exponential_factory/loot_table/blocks loot-tables

        //Raw ore blocks
        dropSelf(ModBlocks.RAW_TINIUM_BLOCK.get());
        //Blocks made from ingredients
        dropSelf(ModBlocks.TINIUM_BLOCK.get());
        dropSelf(ModBlocks.TUENIUM_BLOCK.get());
        dropSelf(ModBlocks.TRINIUM_BLOCK.get());
        //Ore blocks
        add(ModBlocks.TINIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.TINIUM_ORE.get(), ModItems.RAW_TINIUM.get()));
        add(ModBlocks.DEEPSLATE_TINIUM_ORE.get(),
                block -> createManyOreDrops(ModBlocks.DEEPSLATE_TINIUM_ORE.get(), ModItems.RAW_TINIUM.get(), 2, 3));
        add(ModBlocks.DEEPSLATE_TUENIUM_ORE.get(),
                block -> createManyOreDrops(ModBlocks.DEEPSLATE_TUENIUM_ORE.get(), ModItems.TUENIUM_INGOT.get(), 2, 4));
        add(ModBlocks.NETHERRACK_TRINIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.NETHERRACK_TRINIUM_ORE.get(), ModItems.TRINIUM_INGOT.get()));
        //Custom Blocks
        dropSelf(ModBlocks.CAPSULE.get());
        dropSelf(ModBlocks.SOLID_WATER.get());
        dropSelf(ModBlocks.SOLID_WATER_ADVANCED.get());
        dropSelf(ModBlocks.SOLID_LAVA.get());
        dropSelf(ModBlocks.SOLID_GLOWSTONE.get());

        //Storage Blocks
        add(ModBlocks.THERMAL_BATTERY.get(),
                block -> energyBlockDrop(ModBlocks.THERMAL_BATTERY.get()));

        //Crafting Blocks
        add(ModBlocks.SIMPLE_MILL.get(),
                block -> energyBlockDrop(ModBlocks.SIMPLE_MILL.get()));

        //Generator Blocks
        add(ModBlocks.COMBUSTION_GENERATOR.get(),
                block -> energyBlockDrop(ModBlocks.COMBUSTION_GENERATOR.get()));

    }

    protected LootTable.Builder createManyOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    protected LootTable.Builder energyBlockDrop(Block block) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block)
                                .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                                .include(ModDataComponents.ENERGY.get())
                                        // .include(DataComponents.CUSTOM_NAME) //For future, to add other components that get copied. Like upgrades
                                )
                        )
                );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
