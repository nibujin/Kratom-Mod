package net.nibujin.kratom.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.nibujin.kratom.KratomMod;

public class ModRecipes {
    public static final RecipeSerializer<KratomBottleBrewRecipe> KRATOM_BOTTLE_BREW_SERIALIZER =
            Registry.register(
                    BuiltInRegistries.RECIPE_SERIALIZER,
                    Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "crafting_special_kratombottlebrew"),
                    new RecipeSerializer<>(
                            MapCodec.unit(new KratomBottleBrewRecipe()),
                            StreamCodec.unit(new KratomBottleBrewRecipe())
                    )
            );

    public static void registerRecipes() {
        KratomMod.LOGGER.info("Registering Recipe Serializers for " + KratomMod.MOD_ID);
    }
}