package com.lazl0.expfactory.worldgen;

import com.lazl0.expfactory.ExpFactory;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> OVERWORLD_TINIUM_ORE_PLACED_KEY = resourceKey("overworld_tinium_ore_placed");
    public static final ResourceKey<PlacedFeature> OVERWORLD_TUENIUM_ORE_PLACED_KEY = resourceKey("overworld_tuenium_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_TRINIUM_ORE_PLACED_KEY = resourceKey("nether_trinium_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, OVERWORLD_TINIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TINIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(63))));
        register(context, OVERWORLD_TUENIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TUENIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0))));
        register(context, NETHER_TRINIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_TRINIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(31))));
    }

    public static ResourceKey<PlacedFeature> resourceKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(ExpFactory.MODID, name));
    }

    private static void register(
            BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers
    ){
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
