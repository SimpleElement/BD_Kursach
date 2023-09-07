package com.example.bd_kursach.rowmappers;

import com.example.bd_kursach.models.Quest;
import com.example.bd_kursach.models.QuestEnemies;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestEnemiesRowMapper implements RowMapper<QuestEnemies> {
    @Override
    public QuestEnemies mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuestEnemies(rs.getInt("quest_id"), rs.getString("icon_link"),
                rs.getString("name"), rs.getString("description"),
                rs.getInt("hp"), rs.getInt("attack"));
    }
}
