package net.nibujin.kratom;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import net.nibujin.kratom.creativemodetab.ModCreativeModeTabs;
import net.nibujin.kratom.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KratomMod implements ModInitializer {
	public static final String MOD_ID = "kratom";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModCreativeModeTabs.registerModCreativeTabs();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
