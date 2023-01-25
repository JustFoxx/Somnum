package io.github.justfoxx.unethly.registry;


import io.github.justfoxx.unethly.Main;
import io.github.justfoxx.unethly.powers.Poison;
import io.github.justfoxx.unethly.powers.SizeChange;

public class Powers {
    public void init() {
        Main.registry.add(RegistryTypes.POWER, Main.g.id("poison"), new Poison(Main.g.id("poisonous")));
        Main.registry.add(RegistryTypes.POWER, Main.g.id("size"), new SizeChange(Main.g.id("big")));
    }
}
