package io.github.justfoxx.unethly.registry;


import io.github.justfoxx.unethly.Main;
import io.github.justfoxx.unethly.powers.Poison;

public class Powers {
    public void init() {
        Main.registry.add(RegistryTypes.POWER, Main.g.id("poison"), new Poison(Main.g.id("poisonous")));
    }
}
