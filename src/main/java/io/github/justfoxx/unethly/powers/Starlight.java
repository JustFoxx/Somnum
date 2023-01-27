package io.github.justfoxx.unethly.powers;

import io.github.justfoxx.unethly.helpers.Chance;
import io.github.justfoxx.unethly.helpers.MoonPhases;
import io.github.justfoxx.unethly.interfaces.IEDamage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.Random;

public class Starlight extends PowerWrapperImpl implements IEDamage {
    private final Random random = new Random();
    public Starlight(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void onDamage(LivingEntity livingEntity, DamageSource damageSource) {
        int power = 0;
        int time = 15;
        int witherChance = 25;

        if (!(livingEntity.getWorld() instanceof ServerWorld serverWorld)) return;
        if (!(damageSource.getAttacker() instanceof LivingEntity attacker)) return;
        if (MoonPhases.getMoonPhase(serverWorld) == MoonPhases.FULL) {
            power = 1;
            time = 25;
            witherChance = 75;
        } else if (MoonPhases.getMoonPhase((ServerWorld) livingEntity.getWorld()) == MoonPhases.NEW) {
            witherChance = 0;
        }

        addStatusEffects(livingEntity, attacker, witherChance, time, power);
    }

    private void addStatusEffects(LivingEntity livingEntity, LivingEntity attacker, double witherChance, int time, int power) {
        var chance = new Chance(random.nextInt(Chance.doubleToPercentInt(100)-1)+1);
        var poisonEffect = new StatusEffectInstance(StatusEffects.POISON, time*10, power, true, true, false);

        livingEntity.addStatusEffect(poisonEffect);

        if (!attacker.isInvisible()) return;

        var darknessEffect = new StatusEffectInstance(StatusEffects.BLINDNESS, time*10, 0, true, true, false);
        var glowEffect = new StatusEffectInstance(StatusEffects.GLOWING, 30*20, 0, true, false, false);
        var effect = new StatusEffectInstance(StatusEffects.WITHER, 20*10, 0, true, true, false);

        attacker.addStatusEffect(glowEffect);
        livingEntity.addStatusEffect(darknessEffect);

        if (!chance.chanceNext(Chance.doubleToPercentInt(witherChance))) return;

        livingEntity.addStatusEffect(effect);
    }

}
