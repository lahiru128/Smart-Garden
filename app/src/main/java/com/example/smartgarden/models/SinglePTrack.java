package com.example.smartgarden.MODELS;

public class SinglePTrack {
    int spid;
    int ptid;
    String pid;
    String completedstep;
    String completeddate;

    public SinglePTrack() {
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public int getPtid() {
        return ptid;
    }

    public void setPtid(int ptid) {
        this.ptid = ptid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCompletedstep() {
        return completedstep;
    }

    public void setCompletedstep(String completedstep) {
        this.completedstep = completedstep;
    }

    public String getCompleteddate() {
        return completeddate;
    }

    public void setCompleteddate(String completeddate) {
        this.completeddate = completeddate;
    }
}
