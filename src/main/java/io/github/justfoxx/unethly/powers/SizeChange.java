package io.github.justfoxx.unethly.powers;

import io.github.apace100.apoli.power.Power;
import io.github.justfoxx.unethly.interfaces.IEGetEntity;
import io.github.justfoxx.unethly.interfaces.IERemoved;
import io.github.justfoxx.unethly.interfaces.IETicking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

public class SizeChange extends PowerWrapperImpl implements IETicking, IERemoved {
    public final float baseScale = 10/7F;
    public SizeChange(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void tick(LivingEntity livingEntity) {
        final ScaleData baseData = ScaleTypes.BASE.getScaleData(livingEntity);

        if(baseData.getScale() != baseScale) baseData.setTargetScale(baseScale);
    }

    @Override
    public void onRemoved(Power powerInstance) {
        LivingEntity livingEntity = ((IEGetEntity) powerInstance).getEntity();

        final ScaleData baseData = ScaleTypes.BASE.getScaleData(livingEntity);

        if(baseData.getScale() != 1) baseData.setTargetScale(1);
    }
}
