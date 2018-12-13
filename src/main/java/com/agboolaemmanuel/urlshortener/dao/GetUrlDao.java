package com.agboolaemmanuel.urlshortener.dao;

import com.agboolaemmanuel.urlshortener.model.Url;

import javax.sql.DataSource;

public interface GetUrlDao {

    public void setDataSource(DataSource ds);

    public Url getLongUrl(String url);
}
