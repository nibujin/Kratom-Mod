package net.nibujin.kratom.loot;

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nibujin.kratom.item.ModItems;

public class ModLootTableModifiers {
    public static void ModifyLootTables(ResourceKey<LootTable> key,
                                        FabricLootTableBuilder builder,
                                        LootTableSource source,
                                        HolderLookup.Provider provider) {
        if (key.identifier().equals(Identifier.withDefaultNamespace("blocks/jungle_leaves"))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.02f))
                    .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f)).build());

            builder.pool(poolBuilder.build());
        }
        if (key.identifier().equals(Identifier.withDefaultNamespace("blocks/mangrove_leaves"))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.01f))
                    .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)).build());

            builder.pool(poolBuilder.build());
        }

        if(BuiltInLootTables.VILLAGE_PLAINS_HOUSE.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.35f))
                    .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());

            builder.pool(poolBuilder.build());
        }
        if(BuiltInLootTables.VILLAGE_SHEPHERD.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.35f))
                    .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f)).build());

            builder.pool(poolBuilder.build());
        }
        if(BuiltInLootTables.VILLAGE_SAVANNA_HOUSE.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.25f))
                    .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());

            builder.pool(poolBuilder.build());
        }
        if(BuiltInLootTables.VILLAGE_DESERT_HOUSE.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.2f))
                    .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)).build());

            builder.pool(poolBuilder.build());
        }
        if(BuiltInLootTables.JUNGLE_TEMPLE.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.5f))
                    .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 6f)).build());

            builder.pool(poolBuilder.build());
        }
        if(BuiltInLootTables.DESERT_PYRAMID.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.4f))
                    .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)).build());

            builder.pool(poolBuilder.build());
        }
        if(key.identifier().equals(Identifier.withDefaultNamespace("entities/zombie"))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.1f))
                    .add(LootItem.lootTableItem(ModItems.KRATOM_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)).build());

            builder.pool(poolBuilder.build());
        }
    }
}
