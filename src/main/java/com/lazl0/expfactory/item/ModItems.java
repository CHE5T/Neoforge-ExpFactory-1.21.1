package com.lazl0.expfactory.item;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.item.custom.FuelItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExpFactory.MODID);

    //Raw ores
    public static final DeferredItem<Item> RAW_TINIUM = ITEMS.register("raw_tinium",
            () -> new Item(new Item.Properties()));

    //Like tin, but has a more special name
    public static final DeferredItem<Item> TINIUM_INGOT = ITEMS.register("tinium_ingot",
            () -> new Item(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tinium_ingot"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    //Kinda like two, for stage 2 or something
    public static final DeferredItem<Item> TUENIUM_INGOT = ITEMS.register("tuenium_ingot",
            () -> new Item(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_ingot"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> SYNTHETIC_TINIUM = ITEMS.register("synthetic_tinium",
            () -> new FuelItem(new Item.Properties().food(ModFoodProperties.SYNTHETIC_TINIUM), 2000){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if(Screen.hasShiftDown()){
                        tooltipComponents.add(Component.translatable("tooltip.exponential_factory.synthetic_tinium.shift_down"));
                    }else{
                        tooltipComponents.add(Component.translatable("tooltip.exponential_factory.synthetic_tinium"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
