import java.util.Random;

interface AttackStrategy {
    void attack(Character character);
}


class MeleeAttack implements AttackStrategy {
    @Override
    public void attack(Character character) {
        System.out.println(character.getName() + " attacks with a melee weapon.");
    }
}

class RangedAttack implements AttackStrategy {
    @Override
    public void attack(Character character) {
        System.out.println(character.getName() + " attacks with a ranged weapon.");
    }
}

class MagicAttack implements AttackStrategy {
    @Override
    public void attack(Character character) {
        System.out.println(character.getName() + " attacks with magic.");
    }
}


class Character {
    private String name;
    private AttackStrategy attackStrategy;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public void attack() {
        attackStrategy.attack(this);
    }
}


public class StrategyPatternDemo {
    public static void main(String[] args) {
        Character character = new Character("Warrior");

       
        character.setAttackStrategy(new MeleeAttack());
        character.attack(); // Output: Warrior attacks with a melee weapon.

        character.setAttackStrategy(new RangedAttack());
        character.attack(); // Output: Warrior attacks with a ranged weapon.

        character.setAttackStrategy(new MagicAttack());
        character.attack(); // Output: Warrior attacks with magic.
    }
}