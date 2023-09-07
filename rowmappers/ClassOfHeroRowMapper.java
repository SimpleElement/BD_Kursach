package com.example.bd_kursach.rowmappers;

import com.example.bd_kursach.models.ClassOfHero;
import com.example.bd_kursach.models.Hero;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassOfHeroRowMapper implements RowMapper<ClassOfHero> {
    @Override
    public ClassOfHero mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ClassOfHero(rs.getInt("id"), rs.getString("icon_link"),
                rs.getString("class_name"), rs.getString("description"),
                rs.getInt("hp"), rs.getInt("attack"));
    }
}
