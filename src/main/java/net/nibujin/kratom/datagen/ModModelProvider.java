package net.nibujin.kratom.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.nibujin.kratom.block.ModBlocks;
import net.nibujin.kratom.block.custom.KratomCropBlock;
import net.nibujin.kratom.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.KRATOM_LEAF_BALE, TexturedModel.COLUMN);
        blockModelGenerators.createCropBlock(ModBlocks.KRATOM_CROP, KratomCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.KRATOM_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.KRATOM_LEAF, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.KRATOM_SEEDS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.WOODEN_MORTAR_AND_PESTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.STONE_MORTAR_AND_PESTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.KRATOM_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DRIED_KRATOM_LEAF, ModelTemplates.FLAT_ITEM);
    }
}
