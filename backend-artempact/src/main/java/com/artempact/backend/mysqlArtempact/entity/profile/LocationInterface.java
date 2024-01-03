package com.artempact.backend.mysqlArtempact.entity.profile;

public interface LocationInterface {
    String getCity();
    String getProvince();
    Double getLat();
    Double getLon();
    void setLat(Double lat);
    void setLon(Double lon);
}

