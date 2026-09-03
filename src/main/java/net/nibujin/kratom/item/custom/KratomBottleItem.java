package net.nibujin.kratom.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.nibujin.kratom.data.KratomStrain;
import net.nibujin.kratom.data.ModDataComponents;

import java.util.List;
import java.util.function.Consumer;

public class KratomBottleItem extends Item {
    public KratomBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        super.finishUsingItem(stack, level, livingEntity);

        if (!level.isClientSide()) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 1200, 0));
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }

        if (livingEntity instanceof Player player && !player.hasInfiniteMaterials()) {
            ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
            if (!player.getInventory().add(bottle)) {
                player.drop(bottle, false);
            }
        }

        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.DRINK;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        KratomStrain strain = itemStack.getOrDefault(ModDataComponents.STRAIN, KratomStrain.GREEN);
        int dose = itemStack.getOrDefault(ModDataComponents.DOSE, 1);

        MutableComponent strainLine = Component.literal("Strain: ").withStyle(ChatFormatting.GRAY);

        if (strain == KratomStrain.MIXED) {
            strainLine.append(Component.literal("Mixed").withStyle(ChatFormatting.WHITE));
            List<KratomStrain> mixedStrains = itemStack.get(ModDataComponents.MIXED_STRAINS);

            if (mixedStrains != null && !mixedStrains.isEmpty()) {
                strainLine.append(Component.literal(" (").withStyle(ChatFormatting.GRAY));
                for (int i = 0; i < mixedStrains.size(); i++) {
                    strainLine.append(mixedStrains.get(i).getFormattedComponent());
                    if (i < mixedStrains.size() - 1) {
                        strainLine.append(Component.literal(", ").withStyle(ChatFormatting.GRAY));
                    }
                }
                strainLine.append(Component.literal(")").withStyle(ChatFormatting.GRAY));
            }
        } else {
            strainLine.append(strain.getFormattedComponent());
        }

        builder.accept(strainLine);
        builder.accept(Component.literal("Dose: " + dose + " g"));

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}