package com.agboolaemmanuel.urlshortener.service;

import com.agboolaemmanuel.urlshortener.model.Url;
import com.agboolaemmanuel.urlshortener.util.UrlConverter;
import com.agboolaemmanuel.urlshortener.util.UrlJdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UrlShortener {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortener.class);

    public UrlJdbcTemplate urlJdbcTemplate = new UrlJdbcTemplate();


    public String shortenURL(String siteUrl, String longUrl) {
        try {
            Url urlId = urlJdbcTemplate.getId();
            Long id = urlId.getId();
            String uniqueID = UrlConverter.URL_CONVERTER.createUniqueID(id);
            LOGGER.info("Shortenened Url {}", uniqueID);
            LOGGER.info("Shortening {}", siteUrl);
            String baseString = formatLocalURLFromShortener(siteUrl);
            String shortenedURL = baseString + uniqueID;
            LOGGER.info("Shortened Url {}", shortenedURL);
            return shortenedURL;
        } catch (NullPointerException e) {
            return e.getMessage();
        }
    }

    private String formatLocalURLFromShortener(String siteUrl) {
        String[] addressComponents = siteUrl.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }

}
