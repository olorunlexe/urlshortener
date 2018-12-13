package com.agboolaemmanuel.urlshortener.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UrlValidator {
    public static final UrlValidator URL_VALIDATOR = new UrlValidator();

    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]" +
            "+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    private UrlValidator() {
    }

    public boolean validateURL(String url) {
        Matcher match = URL_PATTERN.matcher(url);
        return match.matches();
    }
}
