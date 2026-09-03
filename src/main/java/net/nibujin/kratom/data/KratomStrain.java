package net.nibujin.kratom.data;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

public enum KratomStrain implements StringRepresentable {
    RED("red", "Red", ChatFormatting.RED),
    GREEN("green", "Green", ChatFormatting.DARK_GREEN),
    WHITE("white", "White", ChatFormatting.WHITE),
    MIXED("mixed", "Mixed", ChatFormatting.WHITE);

    public static final Codec<KratomStrain> CODEC = StringRepresentable.fromEnum(KratomStrain::values);
    public static final StreamCodec<ByteBuf, KratomStrain> STREAM_CODEC = ByteBufCodecs.idMapper(
            i -> KratomStrain.values()[i],
            KratomStrain::ordinal
    );

    private final String name;
    private final String displayName;
    private final ChatFormatting formatting;

    KratomStrain(String name, String displayName, ChatFormatting formatting) {
        this.name = name;
        this.displayName = displayName;
        this.formatting = formatting;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public Component getFormattedComponent() {
        return Component.literal(this.displayName).withStyle(this.formatting);
    }
}