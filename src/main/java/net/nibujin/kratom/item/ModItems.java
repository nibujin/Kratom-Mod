package net.nibujin.kratom.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.nibujin.kratom.KratomMod;
import net.nibujin.kratom.data.KratomStrain;
import net.nibujin.kratom.data.ModDataComponents;
import net.nibujin.kratom.item.custom.KratomBottleItem;
import net.nibujin.kratom.item.custom.KratomStrainItem;

import java.util.function.Function;

public class ModItems {

    public static final Item KRATOM_POWDER = registerItem("kratom_powder", properties ->
            new KratomStrainItem(properties.component(ModDataComponents.STRAIN, KratomStrain.GREEN)));
    public static final Item KRATOM_LEAF = registerItem("kratom_leaf", properties ->
            new KratomStrainItem(properties.component(ModDataComponents.STRAIN, KratomStrain.GREEN)));
    public static final Item DRIED_KRATOM_LEAF = registerItem("dried_kratom_leaf", properties ->
            new KratomStrainItem(properties.component(ModDataComponents.STRAIN, KratomStrain.GREEN)));
    public static final Item KRATOM_SEEDS = registerItem("kratom_seeds", Item::new);
    public static final Item WOODEN_MORTAR_AND_PESTLE = registerItem("wooden_mortar_and_pestle", Item::new);
    public static final Item STONE_MORTAR_AND_PESTLE = registerItem("stone_mortar_and_pestle", Item::new);
    public static final Item KRATOM_BOTTLE = registerItem("kratom_bottle", properties ->
            new KratomBottleItem(properties
                    .stacksTo(1)
                    .component(ModDataComponents.STRAIN, KratomStrain.GREEN)
                    .component(ModDataComponents.DOSE, 1)
                    .component(DataComponents.CONSUMABLE, Consumable.builder()
                            .consumeSeconds(1.6f)
                            .animation(ItemUseAnimation.DRINK)
                            .sound(SoundEvents.GENERIC_DRINK)
                            .hasConsumeParticles(false)
                            .build())));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, name)))));
    }

    public static void registerModItems() {
        KratomMod.LOGGER.info("Registering Mod Items for " + KratomMod.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(fabricCreativeModeTabOutput -> {
            fabricCreativeModeTabOutput.accept(KRATOM_POWDER);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(fabricCreativeModeTabOutput -> {
            fabricCreativeModeTabOutput.accept(KRATOM_LEAF);
            fabricCreativeModeTabOutput.accept(DRIED_KRATOM_LEAF);
            fabricCreativeModeTabOutput.accept(KRATOM_SEEDS);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(fabricCreativeModeTabOutput -> {
            fabricCreativeModeTabOutput.accept(WOODEN_MORTAR_AND_PESTLE);
            fabricCreativeModeTabOutput.accept(STONE_MORTAR_AND_PESTLE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(fabricCreativeModeTabOutput -> {
            fabricCreativeModeTabOutput.accept(KRATOM_BOTTLE);
        });
    }
}
