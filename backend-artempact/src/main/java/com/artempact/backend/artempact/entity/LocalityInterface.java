package com.artempact.backend.artempact.entity;

public interface LocalityInterface {
    String getCity();
    String getProvince();
    Double getLat();
    Double getLon();
    void setLat(Double lat);
    void setLon(Double lon);
}
