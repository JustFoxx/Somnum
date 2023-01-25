package io.github.justfoxx.unethly.mixins;

import io.github.apace100.apoli.component.PowerHolderComponentImpl;
import io.github.apace100.apoli.power.Power;
import io.github.justfoxx.unethly.Main;
import io.github.justfoxx.unethly.interfaces.IEPowerWrapper;
import io.github.justfoxx.unethly.interfaces.IERemoved;
import io.github.justfoxx.unethly.registry.RegistryTypes;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PowerHolderComponentImpl.class)
public class PowerHolderComponentImplMixin {
    @Redirect(method = "removePower", at = @At(value = "INVOKE", target = "Lio/github/apace100/apoli/power/Power;onRemoved()V"))
    public void onRemovePower(Power powerInstance) {
        Identifier powerId = powerInstance.getType().getIdentifier();
        IEPowerWrapper power = Main.registry.get(RegistryTypes.POWER, Main.g.id("size"));

        if (!powerInstance.isActive()) {
            powerInstance.onRemoved();
            return;
        }
        if (!power.getId().equals(powerId)) {
            powerInstance.onRemoved();
            return;
        }
        if(!(power instanceof IERemoved powerRemoved)) {
            powerInstance.onRemoved();
            return;
        }

        powerRemoved.onRemoved(powerInstance);
        powerInstance.onRemoved();
    }
}
