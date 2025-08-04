package com.lazl0.expfactory.datagen;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExpFactory.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.TINIUM_INGOT.get());
        basicItem(ModItems.SYNTHETIC_TINIUM.get());
        basicItem(ModItems.TUENIUM_INGOT.get());

        basicItem(ModItems.RAW_TINIUM.get());
    }
}
