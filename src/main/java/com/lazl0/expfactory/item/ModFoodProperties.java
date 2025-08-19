package com.lazl0.expfactory.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties SYNTHETIC_TINIUM = new FoodProperties.Builder().nutrition(8).saturationModifier(0.75f)
            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 100), 0.40f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 3), 0.40f)
            .build();
    public static final FoodProperties SYNTHETIC_TUENIUM = new FoodProperties.Builder().nutrition(6).saturationModifier(1.5f)
            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 100), 0.40f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 3), 0.40f)

            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 120, 1), 0.50f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 120, 5), 0.50f)
            .build();
}
