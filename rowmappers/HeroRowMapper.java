package com.example.bd_kursach.rowmappers;

import com.example.bd_kursach.models.ClassOfHero;
import com.example.bd_kursach.models.Hero;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HeroRowMapper implements RowMapper<Hero> {
    @Override
    public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Hero(rs.getInt("id"), rs.getString("username"),
                new ClassOfHero(rs.getInt("class_id"), rs.getString("icon_link"),
                        rs.getString("class_name"),rs.getString("description"),
                        rs.getInt("class_hp"), rs.getInt("class_attack")),
                        rs.getInt("hp"), rs.getInt("attack")
        );
    }
}
