package net.nibujin.kratom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nibujin.kratom.block.ModBlocks;
import net.nibujin.kratom.block.custom.KratomCropBlock;
import net.nibujin.kratom.data.KratomStrain;
import net.nibujin.kratom.data.ModDataComponents;
import net.nibujin.kratom.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.KRATOM_LEAF_BALE);

        LootItemCondition.Builder whiteVein = AnyOfCondition.anyOf(
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KRATOM_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KratomCropBlock.AGE, 3)),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KRATOM_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KratomCropBlock.AGE, 4))
        );
        LootItemCondition.Builder greenVein = AnyOfCondition.anyOf(
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KRATOM_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KratomCropBlock.AGE, 5)),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KRATOM_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KratomCropBlock.AGE, 6))
        );
        LootItemCondition.Builder matureRedVein = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KRATOM_CROP)
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(KratomCropBlock.AGE, KratomCropBlock.MAX_AGE));
        LootItemCondition.Builder hasResinAge = AnyOfCondition.anyOf(
                whiteVein,
                greenVein,
                matureRedVein
        );
        this.add(ModBlocks.KRATOM_CROP, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS)))
                .withPool(LootPool.lootPool()
                        .when(greenVein)
                        .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .when(matureRedVein)
                        .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))))
                .withPool(LootPool.lootPool()
                        .when(whiteVein)
                        .add(LootItem.lootTableItem(ModItems.KRATOM_LEAF)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                .apply(SetComponentsFunction.setComponent(ModDataComponents.STRAIN, KratomStrain.WHITE))))
                .withPool(LootPool.lootPool()
                        .when(greenVein)
                        .add(LootItem.lootTableItem(ModItems.KRATOM_LEAF)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(SetComponentsFunction.setComponent(ModDataComponents.STRAIN, KratomStrain.GREEN))))
                .withPool(LootPool.lootPool()
                        .when(matureRedVein)
                        .add(LootItem.lootTableItem(ModItems.KRATOM_LEAF)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(SetComponentsFunction.setComponent(ModDataComponents.STRAIN, KratomStrain.RED))))
                .withPool(LootPool.lootPool()
                        .when(hasResinAge)
                        .when(LootItemRandomChanceCondition.randomChance(0.25F))
                        .add(LootItem.lootTableItem(ModItems.KRATOM_RESIN)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))));
    }
}
