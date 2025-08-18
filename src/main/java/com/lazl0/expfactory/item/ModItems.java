package com.lazl0.expfactory.item;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.item.custom.FuelItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExpFactory.MODID);

    //Raw ore(s)
    public static final DeferredItem<Item> RAW_TINIUM = ITEMS.register("raw_tinium",
            () -> new TooltipItem(new Item.Properties(), "tooltip.exponential_factory.raw_tinium"));

    //Like tin, but has a more special name
    public static final DeferredItem<Item> TINIUM_INGOT = ITEMS.register("tinium_ingot",
            () -> new TooltipItem(new Item.Properties(), "tooltip.exponential_factory.tinium_ingot"));
    //Kinda like two, for stage 2 or something
    public static final DeferredItem<Item> TUENIUM_INGOT = ITEMS.register("tuenium_ingot",
            () -> new TooltipItem(new Item.Properties(), "tooltip.exponential_factory.tuenium_ingot"));
    public static final DeferredItem<Item> TRINIUM_INGOT = ITEMS.register("trinium_ingot",
            () -> new TooltipItem(new Item.Properties(), "tooltip.exponential_factory.trinium_ingot"));
    //Quadnium/Quadinium Ingot next?

    public static final DeferredItem<Item> SYNTHETIC_TINIUM = ITEMS.register("synthetic_tinium",
            () -> new FuelItem(new Item.Properties().food(ModFoodProperties.SYNTHETIC_TINIUM),
                    "tooltip.exponential_factory.synthetic_tinium",
                    "tooltip.exponential_factory.synthetic_tinium.shift_down",
                    2000));



    //Mod tools start

    //Tuenium tools start
    public static final DeferredItem<SwordItem> TUENIUM_SWORD = ITEMS.register("tuenium_sword",
            () -> new SwordItem(ModToolTiers.TUENIUM, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.TUENIUM, 3, -2.4f))){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_sword"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<AxeItem> TUENIUM_AXE = ITEMS.register("tuenium_axe",
            () -> new AxeItem(ModToolTiers.TUENIUM, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.TUENIUM, 6, -3))){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_axe"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<PickaxeItem> TUENIUM_PICKAXE = ITEMS.register("tuenium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.TUENIUM, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.TUENIUM, 1, -2.8f))){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_pickaxe"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<ShovelItem> TUENIUM_SHOVEL = ITEMS.register("tuenium_shovel",
            () -> new ShovelItem(ModToolTiers.TUENIUM, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.TUENIUM, 1.5f, -3))){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_shovel"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<HoeItem> TUENIUM_HOE = ITEMS.register("tuenium_hoe",
            () -> new HoeItem(ModToolTiers.TUENIUM, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.TUENIUM, -2, -1))){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_hoe"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    //Tuenium tools end

    //Mod tools end



    //Mod armors start

    //Tuenium armors start
    public static final DeferredItem<ArmorItem> TUENIUM_HELMET = ITEMS.register("tuenium_helmet",
            () -> new ArmorItem(ModArmorMaterials.TUENIUM_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_helmet"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<ArmorItem> TUENIUM_CHESTPLATE = ITEMS.register("tuenium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.TUENIUM_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20))){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_chestplate"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<ArmorItem> TUENIUM_LEGGINGS = ITEMS.register("tuenium_leggings",
            () -> new ArmorItem(ModArmorMaterials.TUENIUM_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(20))){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_leggings"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<ArmorItem> TUENIUM_BOOTS = ITEMS.register("tuenium_boots",
            () -> new ArmorItem(ModArmorMaterials.TUENIUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(20))){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.tuenium_boots"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    //Tuenium armors end

    //Mod armors end



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

