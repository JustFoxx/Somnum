package io.github.justfoxx.unethly;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import io.github.ivymc.ivycore.Global;
import io.github.ivymc.ivycore.registry.RegistryBuilder;
import io.github.justfoxx.unethly.registry.Powers;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    public static final Global g = new Global("unethly");
    public static final RegistryBuilder registry = new RegistryBuilder();
    public static final Powers powers = new Powers();
    @Override
    public void onInitialize() {
        MixinExtrasBootstrap.init();

        powers.init();
        g.LOGGER.info("Avatar?");
    }
}
