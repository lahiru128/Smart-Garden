package com.example.smartgarden.models;

public class Step {
    String key;
    String title;
    String duration;
    String description;
    String op1, op2;
    String returnstep;

    public Step() {
    }

    public Step(String key, String title, String duration, String description, String op1, String op2) {
        this.key = key;
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.op1 = op1;
        this.op2 = op2;
    }

    public Step(String key, String title, String duration, String description, String op1, String op2, String returnstep) {
        this.key = key;
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.op1 = op1;
        this.op2 = op2;
        this.returnstep = returnstep;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getReturnstep() {
        return returnstep;
    }

    public void setReturnstep(String returnstep) {
        this.returnstep = returnstep;
    }
}
