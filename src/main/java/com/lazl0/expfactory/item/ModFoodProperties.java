package com.lazl0.expfactory.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties SYNTHETIC_TINIUM = new FoodProperties.Builder().nutrition(8).saturationModifier(0.75f)
            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 100), 0.40f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 3), 0.40f)
            .build();
}
