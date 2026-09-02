package net.nibujin.kratom.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.nibujin.kratom.KratomMod;
import net.nibujin.kratom.item.ModItems;

public class ModCreativeModeTabs {
    public static final CreativeModeTab KRATOM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "kratom"),
            FabricCreativeModeTab.builder().icon( () -> new ItemStack(ModItems.KRATOM_POWDER))
                    .title(Component.translatable("creativemodetab.kratom.kratom"))
                    .displayItems((parameters, output) -> {
                       output.accept(ModItems.KRATOM_POWDER);
                       output.accept(ModItems.KRATOM_LEAF);
                       output.accept(ModItems.DRIED_KRATOM_LEAF);
                       output.accept(ModItems.KRATOM_SEEDS);
                       output.accept(ModItems.KRATOM_BOTTLE);
                       output.accept(ModItems.WOODEN_MORTAR_AND_PESTLE);
                       output.accept(ModItems.STONE_MORTAR_AND_PESTLE);

                    }).build());

    public static void registerModCreativeTabs() {
        KratomMod.LOGGER.info("Registering Creative Mode Tabs for " + KratomMod.MOD_ID);;
    }
}
