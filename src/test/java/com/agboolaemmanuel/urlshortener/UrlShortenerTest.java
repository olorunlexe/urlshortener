package com.agboolaemmanuel.urlshortener;

import com.agboolaemmanuel.urlshortener.service.UrlShortener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UrlShortenerTest {
    @Autowired
    private UrlShortener shortener;

    @Test
    public void shorten() {

        shortener.shortenURL("localhost:8080/shorten","https://www.youtube.com/watch?v=Zr0E2VP24w8&t=217s");

    }
}

