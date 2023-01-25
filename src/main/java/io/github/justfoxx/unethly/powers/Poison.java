package io.github.justfoxx.unethly.powers;

import io.github.justfoxx.unethly.Main;
import io.github.justfoxx.unethly.helpers.MoonPhases;
import io.github.justfoxx.unethly.interfaces.IEDamage;
import io.github.justfoxx.unethly.interfaces.IETicking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.Objects;

public class Poison extends PowerWrapperImpl implements IEDamage, IETicking {
    public Poison(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void onDamage(LivingEntity livingEntity, DamageSource damageSource) {
        LivingEntity attacker = (LivingEntity) damageSource.getAttacker();
        int power = 1;
        int time = 15;

        if (MoonPhases.getMoonPhase((ServerWorld) livingEntity.getWorld()) == MoonPhases.FULL) {
            power = 1;
            time = 25;
        }

        var effect = new StatusEffectInstance(StatusEffects.POISON, time*10, power, true, true, false);
        attacker.addStatusEffect(effect);
    }

    private int tick = 0;
    private final Random random = Random.create();

    @Override
    public void tick(LivingEntity livingEntity) {
        tick++;

        if (tick % (random.nextInt(199)+1) != 0) return;

        var armourItems = livingEntity.getArmorItems();

        for (var itemStack : armourItems) {
            if (livingEntity instanceof ServerPlayerEntity player) itemStack.damage(2, random, player);
            else itemStack.damage(2, random, null);
        }
    }
}
