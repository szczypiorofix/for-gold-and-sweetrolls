package com.szczypiorofix.sweetrolls.game.enums;

public enum PlayerClass {

    WARRIOR (10, 5, 10, 5) {
        @Override
        public String getName() {
            return "Wojownik";
        }
    },

    CLERIC (8, 6, 8, 8) {
        @Override
        public String getName() {
            return "Kleryk";
        }
    },

    ROGUE (5, 10, 5, 10) {
        @Override
        public String getName() {
            return "Złodziej";
        }
    },

    MAGE (5, 5, 5, 15){
        @Override
        public String getName() {
            return "Mag";
        }
    };

    abstract String getName();
    public static int maxPointes = 30;
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    public int maxHealth;

    PlayerClass(int strength, int dexterity, int constitution, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        maxHealth = 50 + (constitution * 4);
    }


    @Override
    public String toString() {
        return getName();
    }

    public static int getMaxPointes() {
        return maxPointes;
    }
}
