package net.nibujin.kratom.recipe;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.nibujin.kratom.data.KratomStrain;
import net.nibujin.kratom.data.ModDataComponents;
import net.nibujin.kratom.item.ModItems;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class KratomBottleBrewRecipe extends CustomRecipe {
    public KratomBottleBrewRecipe() {
        super();
    }

    private boolean isWaterBottle(ItemStack stack) {
        if (!stack.is(Items.POTION)) return false;
        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
        return contents != null && contents.is(Potions.WATER);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        int bottleCount = 0;
        int powderCount = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (isWaterBottle(stack) || stack.is(ModItems.KRATOM_BOTTLE)) {
                bottleCount++;
            } else if (stack.is(ModItems.KRATOM_POWDER)) {
                powderCount++;
            } else {
                return false;
            }
        }

        return bottleCount == 1 && powderCount >= 1;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        ItemStack baseBottle = ItemStack.EMPTY;
        int powderCount = 0;
        Set<KratomStrain> strainsPresent = new LinkedHashSet<>();

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (isWaterBottle(stack) || stack.is(ModItems.KRATOM_BOTTLE)) {
                baseBottle = stack;
            } else if (stack.is(ModItems.KRATOM_POWDER)) {
                powderCount++;
                KratomStrain powderStrain = stack.getOrDefault(ModDataComponents.STRAIN, KratomStrain.GREEN);
                strainsPresent.add(powderStrain);
            }
        }

        if (baseBottle.isEmpty()) {
            return ItemStack.EMPTY;
        }

        int initialDose = 0;
        if (baseBottle.is(ModItems.KRATOM_BOTTLE)) {
            initialDose = baseBottle.getOrDefault(ModDataComponents.DOSE, 1);
            KratomStrain existingStrain = baseBottle.getOrDefault(ModDataComponents.STRAIN, KratomStrain.GREEN);
            if (existingStrain == KratomStrain.MIXED) {
                List<KratomStrain> existingList = baseBottle.get(ModDataComponents.MIXED_STRAINS);
                if (existingList != null) {
                    strainsPresent.addAll(existingList);
                }
            } else {
                strainsPresent.add(existingStrain);
            }
        }

        int totalDose = initialDose + powderCount;
        ItemStack result = new ItemStack(ModItems.KRATOM_BOTTLE);
        result.set(ModDataComponents.DOSE, totalDose);

        if (strainsPresent.size() == 1) {
            result.set(ModDataComponents.STRAIN, strainsPresent.iterator().next());
        } else {
            result.set(ModDataComponents.STRAIN, KratomStrain.MIXED);
            result.set(ModDataComponents.MIXED_STRAINS, new ArrayList<>(strainsPresent));
        }

        return result;
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return ModRecipes.KRATOM_BOTTLE_BREW_SERIALIZER;
    }
}