package com.example.bd_kursach.models;

public class QuestEnemies {
    private final Integer quest_id;
    private final String icon_link;
    private final String name;
    private final String description;
    private final Integer hp;
    private final Integer attack;

    public QuestEnemies(Integer quest_id, String icon_link, String name, String description, Integer hp, Integer attack) {
        this.quest_id = quest_id;
        this.icon_link = icon_link;
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.attack = attack;
    }

    public Integer getQuest_id() {
        return quest_id;
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

    public Integer getHp() {
        return hp;
    }

    public Integer getAttack() {
        return attack;
    }
}
