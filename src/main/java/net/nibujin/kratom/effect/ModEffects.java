package net.nibujin.kratom.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.nibujin.kratom.KratomMod;

public class ModEffects {
    public static final Holder<MobEffect> SEDATED = registerMobEffect("sedated",
            new MobEffect(MobEffectCategory.BENEFICIAL, 0xC73835) {
                @Override
                public boolean applyEffectTick(final ServerLevel serverLevel, final LivingEntity mob, final int amplification) {
                    int interval = 50 >> amplification;
                    boolean shouldHeal = interval > 0 ? mob.tickCount % interval == 0 : true;

                    if (shouldHeal && mob.getHealth() < mob.getMaxHealth()) {
                        mob.heal(0.5F);
                    }

                    if (mob instanceof Player player) {
                        player.causeFoodExhaustion(0.005F * (float)(amplification + 1));
                    }

                    return true;
                }

                @Override
                public boolean shouldApplyEffectTickThisTick(final int tickCount, final int amplification) {
                    return true;
                }
            }
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
                            2.0D,
                            AttributeModifier.Operation.ADD_VALUE
                    ));

    public static final Holder<MobEffect> STIMULATED = registerMobEffect("stimulated",
            new MobEffect(MobEffectCategory.BENEFICIAL, 0xE5E0C8) {
                @Override
                public boolean applyEffectTick(final ServerLevel serverLevel, final LivingEntity mob, final int amplification) {
                    if (mob instanceof Player player) {
                        player.causeFoodExhaustion(0.005F * (float)(amplification + 1));
                    }

                    if (mob.tickCount % 700 == 0) {
                        mob.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0, false, false, false));
                    }

                    return true;
                }

                @Override
                public boolean shouldApplyEffectTickThisTick(final int tickCount, final int amplification) {
                    return true;
                }
            }
                    .addAttributeModifier(
                            Attributes.MOVEMENT_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.stimulated.speed"),
                            0.10D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.BLOCK_BREAK_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.stimulated.haste"),
                            0.10D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.JUMP_STRENGTH,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.stimulated.jump_boost"),
                            0.06D,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.ATTACK_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.stimulated.attack_speed"),
                            0.10D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.ATTACK_DAMAGE,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.stimulated.weakness"),
                            -2.0D,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.LUCK,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.stimulated.unluck"),
                            -1.5D,
                            AttributeModifier.Operation.ADD_VALUE
                    ));

    public static final Holder<MobEffect> RELAXED = registerMobEffect("relaxed",
            new MobEffect(MobEffectCategory.BENEFICIAL, 0x4E9331) {
                @Override
                public boolean applyEffectTick(final ServerLevel serverLevel, final LivingEntity mob, final int amplification) {
                    int interval = 50 >> amplification;
                    boolean shouldHeal = interval > 0 ? mob.tickCount % interval == 0 : true;

                    if (shouldHeal && mob.getHealth() < mob.getMaxHealth()) {
                        mob.heal(0.5F);
                    }

                    if (mob instanceof Player player) {
                        player.causeFoodExhaustion(0.0025F * (float)(amplification + 1));
                    }

                    if (mob.tickCount % 700 == 0) {
                        mob.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0, false, false, false));
                    }

                    return true;
                }

                @Override
                public boolean shouldApplyEffectTickThisTick(final int tickCount, final int amplification) {
                    return true;
                }
            }
                    .addAttributeModifier(
                            Attributes.MOVEMENT_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.relaxed.slowness"),
                            -0.05D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.BLOCK_BREAK_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.relaxed.haste"),
                            0.15D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.LUCK,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.relaxed.luck"),
                            1.5D,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.ARMOR,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.relaxed.armor"),
                            2.0D,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.ATTACK_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.relaxed.attack_speed"),
                            -0.05D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.ATTACK_DAMAGE,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.relaxed.weakness"),
                            -1.0D,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.JUMP_STRENGTH,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.relaxed.jump_boost"),
                            0.03D,
                            AttributeModifier.Operation.ADD_VALUE
                    ));

    public static final Holder<MobEffect> WOBBLES = registerMobEffect("wobbles",
            new MobEffect(MobEffectCategory.HARMFUL, 0x1E4620) {
                @Override
                public boolean applyEffectTick(final ServerLevel serverLevel, final LivingEntity mob, final int amplification) {
                    if (mob.tickCount % 40 == 0) {
                        mob.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0, false, false, false));
                    }

                    if (mob.tickCount % 200 == 0) {
                        mob.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 0, false, false, false));
                    }

                    if (mob instanceof Player player) {
                        player.causeFoodExhaustion(0.0075F * (float)(amplification + 1));
                    }

                    int poisonInterval = 50 >> amplification;
                    boolean shouldDamage = poisonInterval > 0 ? mob.tickCount % poisonInterval == 0 : true;

                    if (shouldDamage && mob.getHealth() > 1.0F) {
                        mob.hurtServer(serverLevel, mob.damageSources().magic(), 1.0F);
                    }

                    return true;
                }

                @Override
                public boolean shouldApplyEffectTickThisTick(final int tickCount, final int amplification) {
                    return true;
                }
            }
                    .addAttributeModifier(
                            Attributes.MOVEMENT_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.wobbles.slowness"),
                            -0.40D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.BLOCK_BREAK_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.wobbles.mining_fatigue"),
                            -0.30D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.ATTACK_SPEED,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.wobbles.attack_speed"),
                            -0.30D,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            Attributes.ATTACK_DAMAGE,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.wobbles.weakness"),
                            -7.0D,
                            AttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            Attributes.LUCK,
                            Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, "effect.wobbles.unluck"),
                            -2.0D,
                            AttributeModifier.Operation.ADD_VALUE
                    ));

    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(KratomMod.MOD_ID, name), effect);
    }



    public static void registerEffects() {
        KratomMod.LOGGER.info("Registering Mod Effects for " + KratomMod.MOD_ID);
    }
}
