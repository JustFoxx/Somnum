package io.github.justfoxx.unethly.powers;

import io.github.justfoxx.unethly.helpers.MoonPhases;
import io.github.justfoxx.unethly.interfaces.IEDamage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class Starlight extends PowerWrapperImpl implements IEDamage {
    public Starlight(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void onDamage(LivingEntity livingEntity, DamageSource damageSource) {
        LivingEntity attacker = (LivingEntity) damageSource.getAttacker();
        int power = 0;
        int time = 15;

        if (!(livingEntity.getWorld() instanceof ServerWorld serverWorld)) return;

        if (MoonPhases.getMoonPhase(serverWorld) == MoonPhases.FULL) {
            power = 1;
            time = 25;
        }

        var poisonEffect = new StatusEffectInstance(StatusEffects.POISON, time*10, power, true, true, false);
        livingEntity.addStatusEffect(poisonEffect);

        if (!attacker.isInvisible()) return;

        var darknessEffect = new StatusEffectInstance(StatusEffects.BLINDNESS, time*10, 0, true, true, false);
        var glowEffect = new StatusEffectInstance(StatusEffects.GLOWING, 30*20, 0, true, false, false);

        attacker.addStatusEffect(glowEffect);
        livingEntity.addStatusEffect(darknessEffect);
    }

}
