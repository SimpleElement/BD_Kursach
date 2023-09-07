package com.example.bd_kursach.controllers;

import com.example.bd_kursach.models.*;
import com.example.bd_kursach.rowmappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@CrossOrigin
@RestController
public class GetterFromDatabase {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/getItemById/{id}")
    public Item getItemById(@PathVariable("id") Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM items WHERE id = ?", new Object[]{ id }, new ItemRowMapper());
    }

    @GetMapping("getQuestById/{id}")
    public Quest getQuestById(@PathVariable("id") Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM quests WHERE id = ?", new Object[]{ id }, new QuestRowMapper());
    }

    @GetMapping("getHeroesOfUsers/{username}")
    public List<Hero> getHeroesOfUsers(@PathVariable("username") String username) {
        return jdbcTemplate.query("SELECT h.*, c.icon_link, c.class_name, c.description, c.hp as \"class_hp\", c.attack as \"class_attack\" FROM heroes_of_users h " +
                "LEFT JOIN classes_of_heroes c ON h.class_id = c.id " +
                "WHERE h.username = ?", new Object[]{username}, new HeroRowMapper());
    }

    @GetMapping("getItemsOfUser/{username}")
    public List<Item> getItemsOfUser(@PathVariable("username") String username) {
        return jdbcTemplate.query("SELECT * FROM items_of_users iou " +
                "LEFT JOIN items i ON iou.item_id = i.id " +
                "WHERE iou.username = ?", new Object[]{ username }, new ItemRowMapper());
    }

    @GetMapping("getItemsOfHero/{heroId}")
    public List<Item> getItemsOfHero(@PathVariable("heroId") Integer heroId) {
        return jdbcTemplate.query("SELECT * FROM items_of_heroes ioh " +
                "LEFT JOIN items i ON ioh.item_id = i.id " +
                "WHERE ioh.hero_id = ?", new Object[]{ heroId }, new ItemRowMapper());
    }

    @GetMapping("getCostOfItem/{itemId}")
    public Integer getCostOfItem(@PathVariable("itemId") Integer itemId) {
        return jdbcTemplate.queryForObject("SELECT iis.cost_in_gold FROM items_in_store iis " +
                "WHERE iis.item_id = ?", new Object[]{itemId}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("cost_in_gold");
            }
        });
    }

    @GetMapping("getGoldOfUser/{username}")
    public Integer getGoldOfUser(@PathVariable("username") String username) {
        return jdbcTemplate.queryForObject("SELECT u.gold FROM users u " +
                "WHERE u.username = ?", new Object[]{username}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("gold");
            }
        });
    }

    @GetMapping("getItemsInShop")
    public List<ItemWithCost> getItemsInShop() {
        return jdbcTemplate.query("SELECT * FROM items_in_store iin\n" +
                "LEFT JOIN items i ON i.id = iin.item_id", new ItemWithCostRowMapper());
    }

    @GetMapping("getHeroQuest")
    public Quest getHeroQuest(@RequestParam Integer heroId) {
        return jdbcTemplate.queryForObject("SELECT * FROM quests_history qs " +
                        "LEFT JOIN quests q ON qs.quest_id = q.id " +
                        "WHERE hero_id = ? AND status = 'EXPECTS'",
                new Object[]{heroId}, new QuestRowMapper());
    }

    @GetMapping("getQuestEnemies/{questId}")
    public List<QuestEnemies> getQuestEnemies(@PathVariable("questId") Integer questId) {
        return jdbcTemplate.query("SELECT * FROM quest_enemies " +
                "WHERE quest_id = ?", new Object[]{questId}, new QuestEnemiesRowMapper());
    }

    @GetMapping("getAllClasses")
    public List<ClassOfHero> getAllHeroes() {
        return jdbcTemplate.query("SELECT * FROM classes_of_heroes", new ClassOfHeroRowMapper());
    }
}