package net.nibujin.kratom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.nibujin.kratom.block.ModBlocks;
import net.nibujin.kratom.block.custom.KratomCropBlock;
import net.nibujin.kratom.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.KRATOM_LEAF_BALE);

        this.add(ModBlocks.KRATOM_CROP, this.createCropDrops(ModBlocks.KRATOM_CROP, ModItems.KRATOM_LEAF, ModItems.KRATOM_SEEDS,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KRATOM_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KratomCropBlock.AGE, KratomCropBlock.MAX_AGE))));
    }
}
