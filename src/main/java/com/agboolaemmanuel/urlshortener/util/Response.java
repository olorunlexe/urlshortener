package com.agboolaemmanuel.urlshortener.util;


import java.time.LocalDateTime;

public class Response {

    private Long urlId;

    private String urlCode;

    private LocalDateTime createdAt;

    private Boolean status;

    private String description;

    public Response(Long urlId, String urlCode, LocalDateTime createdAt) {
        setUrlId(urlId);
        setUrlCode(urlCode);
        setCreatedAt(createdAt);
    }

    public Long getUrlId() {
        return urlId;
    }

    public String getUrlCode() {
        return urlCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    private void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    private void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }

    private void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    @Override
    public String toString() {
        return "ShortenResponse{" +
                "urlId=" + urlId +
                ", urlCode='" + urlCode + '\'' +
                ", createdAt=" + createdAt + '\'' +
                '}';
    }
}