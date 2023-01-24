package io.github.justfoxx.unethly.interfaces;

import io.github.apace100.apoli.power.VariableIntPower;
import io.github.justfoxx.unethly.helpers.MathEnum;
import net.minecraft.entity.LivingEntity;

public interface IEResource {
    void modifyResource(VariableIntPower power, int value, MathEnum mathEnum, LivingEntity livingEntity);
}
