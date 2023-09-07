package com.example.bd_kursach.models;

public class Item {
    private final Integer id;
    private final String icon_link;
    private final String name;
    private final String description;
    private final String type;
    private final Integer heal_hp;
    private final Integer buff_hp;
    private final Integer buff_attack;

    public Item(Integer id, String icon_link, String name, String description, String type, Integer heal_hp, Integer buff_hp, Integer buff_attack) {
        this.id = id;
        this.icon_link = icon_link;
        this.name = name;
        this.description = description;
        this.type = type;
        this.heal_hp = heal_hp;
        this.buff_hp = buff_hp;
        this.buff_attack = buff_attack;
    }

    public Integer getId() {
        return id;
    }

    public String getIcon_link() {
        return icon_link;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Integer getHeal_hp() {
        return heal_hp;
    }

    public Integer getBuff_hp() {
        return buff_hp;
    }

    public Integer getBuff_attack() {
        return buff_attack;
    }
}
