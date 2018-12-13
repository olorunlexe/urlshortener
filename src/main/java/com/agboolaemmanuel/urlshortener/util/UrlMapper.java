package com.agboolaemmanuel.urlshortener.util;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.agboolaemmanuel.urlshortener.dao.UrlDao;
import com.agboolaemmanuel.urlshortener.model.Url;
import org.springframework.jdbc.core.RowMapper;

public class UrlMapper implements RowMapper<Url> {
    public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
        Url url = new Url();
        url.setId(rs.getLong("id"));
        return url;
    }
}