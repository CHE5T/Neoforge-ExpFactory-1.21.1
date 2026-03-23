package com.lazl0.expfactory.compat;

import com.lazl0.expfactory.block.ModBlocks;
import com.lazl0.expfactory.recipe.ModRecipes;
import com.lazl0.expfactory.recipe.SimpleMillRecipe;
import com.lazl0.expfactory.screen.custom.SimpleMillScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

import static com.lazl0.expfactory.ExpFactory.MODID;

@JeiPlugin
public class JEIExpFactoryPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new SimpleMillRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<RecipeHolder<SimpleMillRecipe>> simpleMillRecipes = recipeManager.getAllRecipesFor(ModRecipes.SIMPLE_MILL_TYPE.get());
        registration.addRecipes(SimpleMillRecipeCategory.SIMPLE_MILL_RECIPE_TYPE, simpleMillRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(SimpleMillScreen.class, 73, 35, 24, 16, SimpleMillRecipeCategory.SIMPLE_MILL_RECIPE_TYPE);
    }

    //override later
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIMPLE_MILL.get()), SimpleMillRecipeCategory.SIMPLE_MILL_RECIPE_TYPE);
    }
}
