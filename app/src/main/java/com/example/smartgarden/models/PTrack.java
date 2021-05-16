package com.example.smartgarden.MODELS;

public class PTrack {
    int id;
    String pid;
    String initialdate;
    String lastcompletedstep;
    String laststepdate;

    public PTrack() {
    }

    public PTrack(int id) {
        this.id = id;
    }

    public PTrack(int id, String pid, String initialdate, String lastcompletedstep, String laststepdate) {
        this.id = id;
        this.pid = pid;
        this.initialdate = initialdate;
        this.lastcompletedstep = lastcompletedstep;
        this.laststepdate = laststepdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(String initialdate) {
        this.initialdate = initialdate;
    }

    public String getLastcompletedstep() {
        return lastcompletedstep;
    }

    public void setLastcompletedstep(String lastcompletedstep) {
        this.lastcompletedstep = lastcompletedstep;
    }

    public String getLaststepdate() {
        return laststepdate;
    }

    public void setLaststepdate(String laststepdate) {
        this.laststepdate = laststepdate;
    }
}
