package net.nibujin.kratom.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.nibujin.kratom.KratomMod;

import java.util.List;

public class ModDataComponents {
    public static final DataComponentType<KratomStrain> STRAIN = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "strain"),
            DataComponentType.<KratomStrain>builder()
                    .persistent(KratomStrain.CODEC)
                    .networkSynchronized(KratomStrain.STREAM_CODEC)
                    .build()
    );

    public static final DataComponentType<Integer> DOSE = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "dose"),
            DataComponentType.<Integer>builder()
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
                    .build()
    );

    public static final DataComponentType<List<KratomStrain>> MIXED_STRAINS = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "mixed_strains"),
            DataComponentType.<List<KratomStrain>>builder()
                    .persistent(KratomStrain.CODEC.listOf())
                    .networkSynchronized(ByteBufCodecs.fromCodec(KratomStrain.CODEC.listOf()))
                    .build()
    );

    public static void registerDataComponents() {
        KratomMod.LOGGER.info("Registering Data Components for " + KratomMod.MOD_ID);
    }
}