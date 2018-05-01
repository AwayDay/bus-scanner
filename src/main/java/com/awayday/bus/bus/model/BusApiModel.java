package com.awayday.bus.bus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusApiModel {
    private String nodeid;
    private String nodenm;
    private String routeid;
    private String routeno;
    private String routetp;
    private int arrprevstationcnt;
    private String vehicletp;
    private int arrtime;

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

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public String getRouteno() {
        return routeno;
    }

    public void setRouteno(String routeno) {
        this.routeno = routeno;
    }

    public String getRoutetp() {
        return routetp;
    }

    public void setRoutetp(String routetp) {
        this.routetp = routetp;
    }

    public int getArrprevstationcnt() {
        return arrprevstationcnt;
    }

    public void setArrprevstationcnt(int arrprevstationcnt) {
        this.arrprevstationcnt = arrprevstationcnt;
    }

    public String getVehicletp() {
        return vehicletp;
    }

    public void setVehicletp(String vehicletp) {
        this.vehicletp = vehicletp;
    }

    public int getArrtime() {
        return arrtime;
    }

    public void setArrtime(int arrtime) {
        this.arrtime = arrtime;
    }
}
