package com.example.bd_kursach.controllers;

import com.example.bd_kursach.models.ClassOfHero;
import com.example.bd_kursach.models.Hero;
import com.example.bd_kursach.models.Quest;
import com.example.bd_kursach.models.QuestEnemies;
import com.example.bd_kursach.rowmappers.ClassOfHeroRowMapper;
import com.example.bd_kursach.rowmappers.HeroRowMapper;
import com.example.bd_kursach.rowmappers.QuestEnemiesRowMapper;
import com.example.bd_kursach.rowmappers.QuestRowMapper;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
public class GameMethods {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("byItemInStore")
    public String byItemInStore(@RequestParam String username, @RequestParam Integer itemId) {
        try {
            jdbcTemplate.query("SELECT byItemInStore(?, ?)", new Object[]{username, itemId}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return 200;
                }
            });
            return "OK";
        } catch (DataAccessException dataAccessException) {
            return dataAccessException.getMessage();
        }
    }

    @PostMapping("sellItemInStore")
    public String sellItemInStore(@RequestParam String username, @RequestParam Integer itemId) {
        try {
            jdbcTemplate.query("SELECT sellItemInStore(?, ?)", new Object[]{username, itemId}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return 200;
                }
            });
            return "OK";
        } catch (DataAccessException dataAccessException) {
            return dataAccessException.getMessage();
        }
    }

    @PostMapping("getItemUserFromHero")
    public String getItemUserFromHero(@RequestParam String username, @RequestParam Integer heroId, @RequestParam Integer itemId) {
        try {
            jdbcTemplate.query("SELECT getitemuserfromhero(?, ?, ?)", new Object[]{username, heroId, itemId}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return 200;
                }
            });
            return "OK";
        } catch (DataAccessException dataAccessException) {
            return dataAccessException.getMessage();
        }
    }

    @PostMapping("getItemHeroFromUser")
    public String getItemHeroFromUser(@RequestParam String username, @RequestParam Integer heroId, @RequestParam Integer itemId) {
        try {
            jdbcTemplate.query("SELECT getItemHeroFromUser(?, ?, ?)", new Object[]{username, heroId, itemId}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return 200;
                }
            });
            return "OK";
        } catch (DataAccessException dataAccessException) {
            return dataAccessException.getMessage();
        }
    }

    @GetMapping("getQuest")
    public Quest getQuest(@RequestParam Integer heroId) {
        jdbcTemplate.query("SELECT getQuest(?)", new Object[]{heroId}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return 200;
            }
        });

        return jdbcTemplate.queryForObject("SELECT * FROM quests_history qs " +
                        "LEFT JOIN quests q ON qs.quest_id = q.id " +
                        "WHERE hero_id = ? AND status = 'EXPECTS'",
                new Object[]{heroId}, new QuestRowMapper());
    }

    @PostMapping("skipQuest")
    public String skipQuest(@RequestParam Integer heroId, @RequestParam Integer questId) {
        jdbcTemplate.query("SELECT endTheQuest(?, ?, 0, 'RUN')", new Object[]{heroId, questId}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return 200;
            }
        });
        return "OK";
    }

    @PostMapping("getQuest")
    public String getQuest(@RequestParam Integer heroId, @RequestParam Integer questId) {
        Hero hero = jdbcTemplate.queryForObject("SELECT h.*, c.icon_link, c.class_name, c.description, c.hp as \"class_hp\", c.attack as \"class_attack\" FROM heroes_of_users h " +
                "LEFT JOIN classes_of_heroes c ON h.class_id = c.id " +
                "WHERE h.id = ?", new Object[]{heroId}, new HeroRowMapper());

        List<QuestEnemies> questEnemies = jdbcTemplate.query("SELECT * FROM quest_enemies " +
                "WHERE quest_id = ?", new Object[]{questId}, new QuestEnemiesRowMapper());

        if (questEnemies.size() == 0) {
            jdbcTemplate.query("SELECT endTheQuest(?, ?, 0, 'COMPLETE')", new Object[]{heroId, questId}, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return 200;
                }
            });
            return "OK";
        } else {
            Integer totalDamage = 0;
            Integer totalHp = 0;

            for (QuestEnemies qE : questEnemies) {
                totalDamage += qE.getAttack();
                totalHp += qE.getHp();
            }

            if (totalDamage >= hero.getHp()) {
                jdbcTemplate.query("SELECT endTheQuest(?, ?, " + (totalDamage + 1) + ", 'FAIL')", new Object[]{heroId, questId}, new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return 200;
                    }
                });
                return "OK";
            } else {
                jdbcTemplate.query("SELECT endTheQuest(?, ?, " + totalDamage + ", 'COMPLETE')", new Object[]{heroId, questId}, new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return 200;
                    }
                });
                return "OK";
            }
        }
    }

    @PostMapping("addHeroFromUser")
    public ClassOfHero addHeroFromUser(@RequestParam String username, @RequestParam Integer heroId) {
        ClassOfHero classOfHero = jdbcTemplate.queryForObject("SELECT * FROM classes_of_heroes " +
                "WHERE id = ?", new Object[]{heroId}, new ClassOfHeroRowMapper());
        jdbcTemplate.query("INSERT INTO heroes_of_users (username, class_id, hp, attack) VALUES (?, ?, ?, ?)", new Object[]{username, heroId, classOfHero.getHp(), classOfHero.getAttack()}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return 200;
            }
        });
        return classOfHero;
    }

    @PostMapping("register")
    public Integer register(@RequestParam String username, @RequestParam String password) {
        jdbcTemplate.query("INSERT INTO users VALUES(?, ?, ?)", new Object[]{username, password, 500}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return 200;
            }
        });
        return 200;
    }

    @PostMapping("auth")
    public Integer auth(@RequestParam String username, @RequestParam String password) {
        return jdbcTemplate.queryForObject("SELECT 1 FROM users WHERE username = ? AND password = ?", new Object[]{username, password}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return 200;
            }
        });
    }
}
