package com.example.bd_kursach.models;

public class ClassOfHero {
    private final Integer id;
    private final String icon_link;
    private final String class_name;
    private final String description;
    private final Integer hp;
    private final Integer attack;

    public ClassOfHero(Integer id, String icon_link, String class_name, String description, Integer hp, Integer attack) {
        this.id = id;
        this.icon_link = icon_link;
        this.class_name = class_name;
        this.description = description;
        this.hp = hp;
        this.attack = attack;
    }

    public Integer getId() {
        return id;
    }

    public String getIcon_link() {
        return icon_link;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getAttack() {
        return attack;
    }
}
