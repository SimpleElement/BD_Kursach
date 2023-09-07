package com.example.bd_kursach.rowmappers;

import com.example.bd_kursach.models.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item(rs.getInt("id"), rs.getString("icon_link"),
                rs.getString("name"), rs.getString("description"),
                rs.getString("type"), rs.getInt("heal_hp"),
                rs.getInt("buff_hp"), rs.getInt("buff_attack"));
    }
}
