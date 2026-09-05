package net.nibujin.kratom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.nibujin.kratom.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.FALL_DAMAGE_RESETTING)
                .add(ModBlocks.KRATOM_LEAF_BALE.builtInRegistryHolder().key());
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.KRATOM_LEAF_BALE.builtInRegistryHolder().key());
        tag(BlockTags.CROPS)
                .add(ModBlocks.KRATOM_CROP.builtInRegistryHolder().key());
    }
}
