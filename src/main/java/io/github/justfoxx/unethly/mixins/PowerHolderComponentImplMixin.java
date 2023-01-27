package io.github.justfoxx.unethly.mixins;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import io.github.apace100.apoli.component.PowerHolderComponentImpl;
import io.github.apace100.apoli.power.Power;
import io.github.justfoxx.unethly.Main;
import io.github.justfoxx.unethly.interfaces.IEPowerWrapper;
import io.github.justfoxx.unethly.interfaces.IERemoved;
import io.github.justfoxx.unethly.registry.RegistryTypes;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(PowerHolderComponentImpl.class)
public class PowerHolderComponentImplMixin {
    @ModifyReceiver(method = "removePower", at = @At(value = "INVOKE", target = "Lio/github/apace100/apoli/power/Power;onRemoved()V"))
    public Power onRemovePower(Power powerInstance) {
        Identifier powerId = powerInstance.getType().getIdentifier();
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("size"));

        if (!powerInstance.isActive()) return powerInstance;
        if (!power.getId().equals(powerId)) return powerInstance;
        if (!(power instanceof IERemoved powerRemoved)) return powerInstance;

        powerRemoved.onRemoved(powerInstance);
        return powerInstance;
    }
}
