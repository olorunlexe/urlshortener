package com.agboolaemmanuel.urlshortener.util;

import javax.sql.DataSource;

import com.agboolaemmanuel.urlshortener.config.DbConfig;
import com.agboolaemmanuel.urlshortener.dao.UrlDao;
import com.agboolaemmanuel.urlshortener.model.Url;
import com.agboolaemmanuel.urlshortener.service.UrlProcedure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class UrlJdbcTemplate implements UrlDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(DbConfig.dataSource());
    }

    @Override
    public Url getId() {
        UrlProcedure urlProcedure = new UrlProcedure(dataSource, "checkIdcount");
        return urlProcedure.execute();
    }
    public  String saveUrl(String shortUrl,String longUrl) throws Exception{
        this.jdbcTemplateObject.setResultsMapCaseInsensitive(true);
        SqlParameterSource params = new MapSqlParameterSource();
        ((MapSqlParameterSource) params).addValue("shortUrl",shortUrl);
        ((MapSqlParameterSource) params).addValue("longUrl",longUrl);

        SimpleJdbcCall insertProcedure = new SimpleJdbcCall(this.jdbcTemplateObject)
                .withSchemaName("dbo")
                .withProcedureName("saveUrl");

        insertProcedure.execute(params);

        String result="Saved to Database Successfully!";
        return result;
    }
}