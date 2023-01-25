package io.github.justfoxx.unethly.mixins;

import io.github.justfoxx.unethly.Main;
import io.github.justfoxx.unethly.interfaces.IEDamage;
import io.github.justfoxx.unethly.interfaces.IEPowerWrapper;
import io.github.justfoxx.unethly.interfaces.IETicking;
import io.github.justfoxx.unethly.registry.RegistryTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"))
    public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("poison"));

        if (!power.isActive((LivingEntity) (Object)this)) return;
        if (!(source.getAttacker() instanceof LivingEntity livingAttacker)) return;
        if (power.isActive(livingAttacker)) return;
        if (!(power instanceof IEDamage damagePower)) return;

        damagePower.onDamage((LivingEntity) (Object)this, source);
    }

    @Inject(method = "canHaveStatusEffect", at = @At("HEAD"), cancellable = true)
    public void poisonEffect(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("poison"));

        if (!power.isActive((LivingEntity) (Object)this)) return;
        if (effect.getEffectType() == StatusEffects.POISON) cir.setReturnValue(false);
    }


    @Inject(method = "tick", at = @At("HEAD"))
    public void onTickPoison(CallbackInfo ci) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("poison"));

        if (!power.isActive((LivingEntity) (Object)this)) return;
        if (!(power instanceof IETicking tickingPower)) return;

        tickingPower.tick((LivingEntity) (Object)this);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTickSizeChang(CallbackInfo ci) {
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("size"));

        if (!power.isActive((LivingEntity) (Object)this)) return;
        if (!(power instanceof IETicking tickingPower)) return;

        tickingPower.tick((LivingEntity) (Object)this);
    }

}
