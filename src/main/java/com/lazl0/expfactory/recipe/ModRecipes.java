package com.lazl0.expfactory.recipe;

import com.lazl0.expfactory.ExpFactory;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, ExpFactory.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, ExpFactory.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SimpleMillRecipe>> SIMPLE_MILL_SERIALIZER =
            SERIALIZERS.register("simple_mill", SimpleMillRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<SimpleMillRecipe>> SIMPLE_MILL_TYPE =
            TYPES.register("simple_mill", () -> new RecipeType<SimpleMillRecipe>() {
                @Override
                public String toString() {
                    return "simple_mill";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
