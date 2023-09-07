package nl.timo.pokedexapi.pokemon;

import nl.timo.pokedexapi.die.Die;

public class Pokemon {
    //    public string Name {get; set;}
    private final String name;
    private Integer maxHp;
    private Integer currentHp;
    private Integer strength;

    public Pokemon(String name, Integer maxHp, Integer strength) {
        this.name = name;
        this.maxHp = this.currentHp = maxHp;
        this.strength = strength;
    }

    public String attack(Pokemon target, Die die) {
        Integer roll = die.roll20();
        Integer hitChance = roll + this.strength;
        Integer armorClass = target.strength * 3;

        if (hitChance > armorClass) {
            target.takeDamage(die.roll8());
            return "Hit!" + appendAttackResultInformation(roll, hitChance, armorClass);
        }

        return "Miss!" + appendAttackResultInformation(roll, hitChance, armorClass);
    }

    private void takeDamage(Integer damage) {
        this.currentHp -= damage;

        if (currentHp < 0) {
            currentHp = 0;
        }
    }

    private String appendAttackResultInformation(Integer roll, Integer hitChance, Integer amorClass) {
        return String.format(" %s (roll) + %s (str) = %s hit vs %s (ac)", roll, strength, hitChance, amorClass);
    }

    public String getName() {
        return name;
    }

    public Integer getCurrentHp() {
        return currentHp;
    }

    public boolean isDead() {
        return currentHp == 0;
    }
}
