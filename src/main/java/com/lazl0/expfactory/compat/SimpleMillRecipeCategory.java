package com.lazl0.expfactory.compat;

import com.lazl0.expfactory.block.ModBlocks;
import com.lazl0.expfactory.recipe.SimpleMillRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import static com.lazl0.expfactory.ExpFactory.MODID;

public class SimpleMillRecipeCategory implements IRecipeCategory<RecipeHolder<SimpleMillRecipe>> {
    public static final RecipeType<RecipeHolder<SimpleMillRecipe>> SIMPLE_MILL_RECIPE_TYPE = RecipeType.create(MODID, "milling", (Class<RecipeHolder<SimpleMillRecipe>>) (Class<?>) RecipeHolder.class);//Idk about this

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/simple_mill/simple_mill_e_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public SimpleMillRecipeCategory(IGuiHelper guiHelper){
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 174, 81);
        this.icon = guiHelper.createDrawableItemStack(ModBlocks.SIMPLE_MILL.toStack());
    }
    @Override
    public RecipeType<RecipeHolder<SimpleMillRecipe>> getRecipeType() {
        return SIMPLE_MILL_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.exponential_factory.simple_mill");
    }

    @Override
    public void draw(RecipeHolder<SimpleMillRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        background.draw(guiGraphics);
    }

    @Override
    public int getWidth() {
        return background.getWidth();
    }

    @Override
    public int getHeight() {
        return background.getHeight();
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<SimpleMillRecipe> recipe, IFocusGroup focuses) {
        builder.addInputSlot(54, 34).addIngredients(recipe.value().getIngredients().getFirst());
        builder.addOutputSlot(104, 34).addItemStack(recipe.value().getResultItem(null));
    }
}
