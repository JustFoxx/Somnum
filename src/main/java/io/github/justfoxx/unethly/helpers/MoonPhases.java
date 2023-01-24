package io.github.justfoxx.unethly.helpers;

import net.minecraft.server.world.ServerWorld;

public enum MoonPhases {
    NEW,
    FULL,
    NORMAL;
    public static MoonPhases getMoonPhase(ServerWorld world) {
        return switch (world.getMoonPhase()) {
            case 5 -> MoonPhases.NEW;
            case 1 -> MoonPhases.FULL;
            default -> MoonPhases.NORMAL;
        };
    }
}
