package net.nibujin.kratom;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.Identifier;

import net.nibujin.kratom.creativemodetab.ModCreativeModeTabs;
import net.nibujin.kratom.item.ModItems;
import net.nibujin.kratom.loot.ModLootTableModifiers;
import net.nibujin.kratom.potion.ModPotions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KratomMod implements ModInitializer {
	public static final String MOD_ID = "kratom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModCreativeModeTabs.registerModCreativeTabs();
		ModPotions.registerPotions();

		LootTableEvents.MODIFY.register(ModLootTableModifiers::ModifyLootTables);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
