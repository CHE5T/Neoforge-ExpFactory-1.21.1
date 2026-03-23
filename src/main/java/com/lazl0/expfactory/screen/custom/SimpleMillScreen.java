package com.lazl0.expfactory.screen.custom;

import com.lazl0.expfactory.ExpFactory;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class SimpleMillScreen extends AbstractContainerScreen<SimpleMillMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(ExpFactory.MODID, "textures/gui/simple_mill/simple_mill_e_gui.png");
    private static final ResourceLocation ARROW_TEXTURE = ResourceLocation.fromNamespaceAndPath(ExpFactory.MODID, "textures/gui/progress_arrow.png");
    private static final ResourceLocation ENERGY_BAR_TEXTURE = ResourceLocation.fromNamespaceAndPath(ExpFactory.MODID, "textures/gui/energy_bar.png");

    public SimpleMillScreen(SimpleMillMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;//Image width should be 203 for use of energy bar
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, 203, imageHeight);

        renderArrowProgress(guiGraphics, x, y);
        renderEnergyBar(guiGraphics, x, y);
    }

    protected void renderArrowProgress(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE, x + 73, y + 35, 0, 0, menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }

    protected void renderEnergyBar(GuiGraphics guiGraphics, int x, int y){
        int scaledEnergy = menu.getScaledEnergy();
        int barHeight = 67;
        if(scaledEnergy > 0){
            guiGraphics.blit(ENERGY_BAR_TEXTURE, x + 179, y + 8 + (barHeight - scaledEnergy), 0, barHeight - scaledEnergy, 16, scaledEnergy, 16, barHeight);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        //Renders the tooltip over the energy bar
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        int energy = this.menu.data.get(2);
        int maxEnergy = this.menu.data.get(3);

        if(isMouseOver(mouseX, mouseY, x + 179, y + 8, 16, 67)){
            List<Component> tooltip = new ArrayList<>();
            tooltip.add(Component.literal(energy + " FE/" + maxEnergy + " FE"));
            guiGraphics.renderTooltip(this.font, tooltip.getFirst(), mouseX, mouseY);
        }
    }

    /**
     * Checks if the mouse cursor is within a given rectangular area.
     * @param pMouseX The X position of the mouse
     * @param pMouseY The Y position of the mouse
     * @param pX The X position of the top-left corner of the rectangle
     * @param pY The Y position of the top-left corner of the rectangle
     * @param pWidth The width of the rectangle
     * @param pHeight The height of the rectangle
     * @return true if the mouse is inside the rectangle, false otherwise
     */
    private boolean isMouseOver(double pMouseX, double pMouseY, int pX, int pY, int pWidth, int pHeight) {
        // Check if the mouse's X position is greater than the rectangle's left edge
        return pMouseX >= (double)pX &&
                // Check if the mouse's Y position is greater than the rectangle's top edge
                pMouseY >= (double)pY &&
                // Check if the mouse's X position is less than the rectangle's right edge
                pMouseX < (double)(pX + pWidth) &&
                // Check if the mouse's Y position is less than the rectangle's bottom edge
                pMouseY < (double)(pY + pHeight);
    }
}
