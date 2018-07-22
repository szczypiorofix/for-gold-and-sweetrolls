package com.szczypiorofix.sweetrolls.game.enums;

public enum PlayerClass {

    // TODO Zrezygnować z klas postaci. Postać będzie bezklasowa i rozwijać będzie tylko umiejętności których używa (jak w TESach)

    // TODO Umiejętności będą wyuczane i zwiększane u trenerów jak w Gothicu

    // TODO Do każdego kafelka na mapie świata można "wejść" gdzie generowany jest losowy teren

    // TODO Dany teren ma określoną ilość zasobów: rudy, drewno, żywność, roślinki które po zebraniu po pewnym czasie (losowo trochę) będzie odnawiany

    // TODO Podróż na mapie głównej zajmować będzie 1 dzień na 1 kafelek (w zależności od typu terenu będize np. więcej w górach).
    // TODO Podróż po mapach wewnętrznych zajmować będzie 10min - 1h w zależności od terenu. Mapa wewnętrzna będzie posiadała określoną wielkość np. 50 x 50.
    // TODO Na mapach wewnętrznych będą możliwe walki, na mapie głównej - nie.

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
