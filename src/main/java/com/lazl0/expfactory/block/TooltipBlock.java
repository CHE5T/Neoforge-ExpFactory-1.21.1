package com.lazl0.expfactory.block;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class TooltipBlock extends Block {
    private String key = "";
    private String key2 = null;

    public TooltipBlock(Block.Properties properties, String key) {
        super(properties);
        this.key = key;
    }
    //Shows key2 when shift is held down
    public TooltipBlock(Block.Properties properties, String key, String key2) {
        super(properties);
        this.key = key;
        this.key2 = key2;
    }

    //Website for what to put in the en_us.json for formatting: https://minecraft.wiki/w/Formatting_codes
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(key2==null){
            tooltipComponents.add(Component.translatable(key));
        }else if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable(key2));
        }else{
            tooltipComponents.add(Component.translatable(key));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
