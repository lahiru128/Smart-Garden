package com.example.smartgarden.MODELS;

public class CurrentTrack {
    String fertilizer_ldate;
    String initial_date;
    String initial_step;
    String initial_wdate;
    String pid;
    String watering_date;

    public CurrentTrack() {
    }

    public CurrentTrack(String fertilizer_ldate, String initial_date, String initial_step, String initial_wdate, String pid, String watering_date) {
        this.fertilizer_ldate = fertilizer_ldate;
        this.initial_date = initial_date;
        this.initial_step = initial_step;
        this.initial_wdate = initial_wdate;
        this.pid = pid;
        this.watering_date = watering_date;
    }

    public String getFertilizer_ldate() {
        return fertilizer_ldate;
    }

    public void setFertilizer_ldate(String fertilizer_ldate) {
        this.fertilizer_ldate = fertilizer_ldate;
    }

    public String getInitial_date() {
        return initial_date;
    }

    public void setInitial_date(String initial_date) {
        this.initial_date = initial_date;
    }

    public String getInitial_step() {
        return initial_step;
    }

    public void setInitial_step(String initial_step) {
        this.initial_step = initial_step;
    }

    public String getInitial_wdate() {
        return initial_wdate;
    }

    public void setInitial_wdate(String initial_wdate) {
        this.initial_wdate = initial_wdate;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getWatering_date() {
        return watering_date;
    }

    public void setWatering_date(String watering_date) {
        this.watering_date = watering_date;
    }
}
