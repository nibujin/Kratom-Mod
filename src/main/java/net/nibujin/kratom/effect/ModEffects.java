package net.nibujin.kratom.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.nibujin.kratom.KratomMod;

public class ModEffects {
    public static final Holder<MobEffect> SEDATED = registerMobEffect("sedated",
            new MobEffect(MobEffectCategory.BENEFICIAL, 0xC73835) {}
                    .addAttributeModifier(
                            Attributes.MOVEMENT_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.sedated.slowness"),
                            -0.10D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.ARMOR,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.sedated.armor"),
                            4.0D,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.ATTACK_DAMAGE,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.sedated.weakness"),
                            -1.0D,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.ATTACK_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.sedated.mining_fatigue"),
                            -0.10D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.LUCK,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.sedated.luck"),
                            1.0D,
                            AttributeModifier.Operation.ADD_VALUE
                    ));

    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, name), effect);
    }



    public static void registerEffects() {
        KratomMod.LOGGER.info("Registering Mod Effects for " + KratomMod.MOD_ID);
    }
}
