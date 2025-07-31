package com.lazl0.expfactory.item;

import com.lazl0.expfactory.ExpFactory;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExpFactory.MODID);

    //Raw ores
    public static final DeferredItem<Item> RAW_TINIUM = ITEMS.register("raw_tinium",
            () -> new Item(new Item.Properties()));

    //Like tin, but has a more special name
    public static final DeferredItem<Item> TINIUM = ITEMS.register("tinium",
            () -> new Item(new Item.Properties()));
    //Kinda like two, for stage 2 or something
    public static final DeferredItem<Item> TUENIUM = ITEMS.register("tuenium",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
