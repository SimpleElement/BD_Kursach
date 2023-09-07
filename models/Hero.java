package com.example.bd_kursach.models;

public class Hero {
    private final Integer id;
    private final String username;
    private final ClassOfHero class_hero;
    private final Integer hp;
    private final Integer attack;

    public Hero(Integer id, String username, ClassOfHero class_hero, Integer hp, Integer attack) {
        this.id = id;
        this.username = username;
        this.class_hero = class_hero;
        this.hp = hp;
        this.attack = attack;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public ClassOfHero getClass_hero() {
        return class_hero;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getAttack() {
        return attack;
    }
}
