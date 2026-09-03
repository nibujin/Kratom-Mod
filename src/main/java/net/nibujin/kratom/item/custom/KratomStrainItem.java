package net.nibujin.kratom.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.nibujin.kratom.data.KratomStrain;
import net.nibujin.kratom.data.ModDataComponents;

import java.util.function.Consumer;

public class KratomStrainItem extends Item {
    public KratomStrainItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if (itemStack.has(ModDataComponents.STRAIN)) {
            KratomStrain strain = itemStack.get(ModDataComponents.STRAIN);
            if (strain != null) {
                builder.accept(Component.literal("Strain: ")
                        .withStyle(ChatFormatting.GRAY)
                        .append(strain.getFormattedComponent()));
            }
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}