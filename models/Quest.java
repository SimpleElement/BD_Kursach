package com.example.bd_kursach.models;

public class Quest {
    private final Integer id;
    private final String name;
    private final String description;
    private final String type;
    private final Integer gold_reward;

    public Quest(Integer id, String name, String description, String type, Integer gold_reward) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.gold_reward = gold_reward;
    }

    public Integer getId() {
        return id;
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

    public Integer getGold_reward() {
        return gold_reward;
    }
}
