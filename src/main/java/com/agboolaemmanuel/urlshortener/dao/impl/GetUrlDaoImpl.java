package com.agboolaemmanuel.urlshortener.dao.impl;

import com.agboolaemmanuel.urlshortener.dao.GetUrlDao;
import com.agboolaemmanuel.urlshortener.model.Url;
import com.agboolaemmanuel.urlshortener.util.RowCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class GetUrlDaoImpl implements GetUrlDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    protected SimpleJdbcCall findByShort;
    protected SimpleJdbcCall create, update, delete, find, findAll;
    protected final String SINGLE_RESULT = "object";
    protected final String MULTIPLE_RESULT = "list";
    protected static final String RESULT_COUNT = "count";

    @Autowired
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("create_agent").withReturnValue();
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("update_agent").withReturnValue();
        find = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_agent")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Url.class));
        findAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_all_agents").returningResultSet(RESULT_COUNT, new RowCountMapper())
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Url.class));
        findByShort = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getLong")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Url.class));
    }

    @Override
    public Url getLongUrl(String url) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("shortUrl", url);
        Map<String, Object> m = findByShort.execute(in);
        List<Url> list = (List<Url>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
