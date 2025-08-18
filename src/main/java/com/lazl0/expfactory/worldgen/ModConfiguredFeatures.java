package com.lazl0.expfactory.worldgen;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TINIUM_ORE_KEY = resourceKey("overworld_tinium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TUENIUM_ORE_KEY = resourceKey("overworld_tuenium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_TRINIUM_ORE_KEY = resourceKey("nether_trinium_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        //RuleTest endstoneReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldTiniumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.TINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_TINIUM_ORE.get().defaultBlockState()));

        //Refer to OreFeatures with an s, for minecraft generation
        register(context, OVERWORLD_TINIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTiniumOres, 12));
        register(context, OVERWORLD_TUENIUM_ORE_KEY, Feature.ORE, new OreConfiguration(deepslateReplaceables,
                ModBlocks.DEEPSLATE_TUENIUM_ORE.get().defaultBlockState(), 8));
        register(context, NETHER_TRINIUM_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.NETHERRACK_TRINIUM_ORE.get().defaultBlockState(), 6));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> resourceKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ExpFactory.MODID, name));
    }

    private static <EF extends FeatureConfiguration, E extends Feature<EF>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, E feature, EF configuration
    ){
      context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
