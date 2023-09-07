package nl.timo.pokedexapi.die;

import java.util.Random;

public class DieImpl implements Die {
    @Override
    public Integer roll20() {
        return new Random().nextInt(20) + 1;
    }

    @Override
    public Integer roll8() {
        return new Random().nextInt(8) + 1;
    }
}