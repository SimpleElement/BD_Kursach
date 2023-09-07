package com.example.bd_kursach.rowmappers;

import com.example.bd_kursach.models.Item;
import com.example.bd_kursach.models.ItemWithCost;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemWithCostRowMapper implements RowMapper<ItemWithCost> {

    @Override
    public ItemWithCost mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ItemWithCost(rs.getInt("id"), rs.getString("icon_link"),
                rs.getString("name"), rs.getString("description"),
                rs.getString("type"), rs.getInt("heal_hp"),
                rs.getInt("buff_hp"), rs.getInt("buff_attack"), rs.getInt("cost_in_gold"));
    }
}
