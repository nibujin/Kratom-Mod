package net.nibujin.kratom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.nibujin.kratom.block.ModBlocks;
import net.nibujin.kratom.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.TOOLS, ModItems.WOODEN_MORTAR_AND_PESTLE, 1)
                        .requires(Items.BOWL)
                        .requires(Items.STICK)
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("kratom")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.STONE_MORTAR_AND_PESTLE, 1)
                        .pattern(" C ")
                        .pattern("CWC")
                        .pattern(" C ")
                        .define('C', Blocks.COBBLESTONE)
                        .define('W', ModItems.WOODEN_MORTAR_AND_PESTLE)
                        .unlockedBy(getHasName(ModItems.WOODEN_MORTAR_AND_PESTLE), has(ModItems.WOODEN_MORTAR_AND_PESTLE))
                        .group("kratom")
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.KRATOM_LEAF_BALE, 1)
                        .pattern("KKK")
                        .pattern("KKK")
                        .pattern("KKK")
                        .define('K', ModItems.KRATOM_LEAF)
                        .unlockedBy(getHasName(ModItems.KRATOM_LEAF), has(ModItems.KRATOM_LEAF))
                        .group("kratom")
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.KRATOM_LEAF, 9)
                        .requires(ModBlocks.KRATOM_LEAF_BALE)
                        .unlockedBy(getHasName(ModBlocks.KRATOM_LEAF_BALE), has(ModBlocks.KRATOM_LEAF_BALE))
                        .group("kratom")
                        .save(output);


            }
        };
    }

    @Override
    public String getName() {
        return "Kratom Recipes";
    }
}
