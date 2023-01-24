package io.github.justfoxx.unethly.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public interface IEDamage {
    void onDamage(LivingEntity livingEntity, DamageSource damageSource);
}
