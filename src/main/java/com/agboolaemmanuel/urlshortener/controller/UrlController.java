package com.agboolaemmanuel.urlshortener.controller;


import com.agboolaemmanuel.urlshortener.dao.GetUrlDao;
import com.agboolaemmanuel.urlshortener.dao.ShortenRequest;
import com.agboolaemmanuel.urlshortener.model.Url;
import com.agboolaemmanuel.urlshortener.service.UrlShortener;
import com.agboolaemmanuel.urlshortener.util.UrlJdbcTemplate;
import com.agboolaemmanuel.urlshortener.util.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UrlController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);
    public UrlShortener urlShortener = new UrlShortener();

    @RequestMapping(value = "/shortener", method = RequestMethod.POST, consumes = {"application/json"}, produces = "application/json")
    public String shortenUrl(@RequestBody @Valid ShortenRequest shortenRequest, HttpServletRequest request) throws Exception {
        LOGGER.info("Received url to shorten: " + shortenRequest.getUrl());
        String longUrl = shortenRequest.getUrl();
        String localUrl = request.getRequestURL().toString();
        if (UrlValidator.URL_VALIDATOR.validateURL(longUrl)) {
            try {
                LOGGER.info(longUrl);
                LOGGER.info(localUrl);
                String shortenedUrl = urlShortener.shortenURL(localUrl, longUrl);
                LOGGER.info("Shortened url to: " + shortenedUrl);
                UrlJdbcTemplate save = new UrlJdbcTemplate();
                LOGGER.info("url saved to Database: " + shortenedUrl);
                save.saveUrl(shortenedUrl, longUrl);
                return shortenedUrl + "  ...:saved to Database for Use";
            } catch (NullPointerException e) {
                return e.getMessage();
            }

        }
        throw new Exception("Please enter a valid URL");
    }

    @Autowired
    public GetUrlDao getUrlDao;

    @RequestMapping(value = "/getOriginalUrl", method = RequestMethod.POST, consumes = {"application/json"}, produces = "application/json")
    public Url getLongUrl(@RequestBody @Valid ShortenRequest shortenRequest) {
        LOGGER.info("Received url to Return Original version: " + shortenRequest.getMiniUrl());
        String shortUrl = shortenRequest.getUrl();

        Url url = getUrlDao.getLongUrl(shortUrl);
        LOGGER.info("Original version: " + url);
        return url;
    }


}

