package com.szczypiorofix.sweetrolls.game.enums;

public enum ItemType {

    DAGGER("sztylet", ItemGroup.WEAPON),
    SWORD("miecz", ItemGroup.WEAPON),
    AXE("topór", ItemGroup.WEAPON),
    HAMMER("młot", ItemGroup.WEAPON),

    POTION("eliksir", ItemGroup.USABLE),
    SCROLL("zwój", ItemGroup.USABLE),

    GOLD("złoto", ItemGroup.STATIC),
    CHEST("skrzynia", ItemGroup.STATIC),

    ARMOR_CHEST("napierśnik", ItemGroup.ARMOR),
    ARMOR_HELMET("napierśnik", ItemGroup.ARMOR),
    ARMOR_GLOVES("napierśnik", ItemGroup.ARMOR),
    ARMOR_BOOTS("napierśnik", ItemGroup.ARMOR),
    ARMOR_SHIELD("napierśnik", ItemGroup.ARMOR),
    ROBE("płaszcz", ItemGroup.ARMOR),

    DEFAULT("domyślny", ItemGroup.STATIC);

    public String name;
    public ItemGroup itemGroup;

    ItemType(String name, ItemGroup itemGroup) {
        this.name = name;
        this.itemGroup = itemGroup;
    }
}
