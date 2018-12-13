package com.agboolaemmanuel.urlshortener.dao;


import com.agboolaemmanuel.urlshortener.model.Url;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;

@Repository
public interface UrlDao {
    public void setDataSource(DataSource ds);

    public Url getId();
}
