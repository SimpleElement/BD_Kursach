package com.example.bd_kursach.rowmappers;

import com.example.bd_kursach.models.Item;
import com.example.bd_kursach.models.Quest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestRowMapper implements RowMapper<Quest> {
    @Override
    public Quest mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Quest(rs.getInt("id"), rs.getString("name"),
                rs.getString("description"), rs.getString("type"),
                rs.getInt("gold_reward"));
    }
}
