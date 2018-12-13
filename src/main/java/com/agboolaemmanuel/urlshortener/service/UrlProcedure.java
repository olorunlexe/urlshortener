package com.agboolaemmanuel.urlshortener.service;

import com.agboolaemmanuel.urlshortener.config.DbConfig;
import com.agboolaemmanuel.urlshortener.model.Url;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Map;

public class UrlProcedure extends StoredProcedure {
    public UrlProcedure(DataSource dataSource, String procedureName) {
        super(DbConfig.dataSource(),procedureName);
        declareParameter(new SqlOutParameter("out_id", Types.BIGINT));
        compile();
    }
    public Url execute(){
        Map<String, Object> out = super.execute();
        Url url = new Url();
        url.setId((Long) out.get("out_id"));
        return url;
    }
}
