package com.example.worklognet1;

public class Shift {
    private String startTime;
    private String endTime;

    // Constructor
    public Shift() {
    }

    // Constructor vs startTime, endTime
    public Shift(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getter and Setter cho startTime
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    // Getter , Setter for endTime
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
