package com.awayday.bus.station.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationApiModel {
    private String citycode;
    private double gpslati;
    private double gpslong;
    private String nodeid;
    private String nodenm;

    public String getArriveBusListApiPath() {
        return String.format("/cities/%s/stations/%s/routes", this.citycode, this.nodeid);
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public double getGpslati() {
        return gpslati;
    }

    public void setGpslati(double gpslati) {
        this.gpslati = gpslati;
    }

    public double getGpslong() {
        return gpslong;
    }

    public void setGpslong(double gpslong) {
        this.gpslong = gpslong;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getNodenm() {
        return nodenm;
    }

    public void setNodenm(String nodenm) {
        this.nodenm = nodenm;
    }
}
