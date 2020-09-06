package com.example.ntpc;

public class TimeDataPojo {

    private String TimeElapsed;

    private String NextBlockTime;

    private int CurrentBlockNumber;

    private  String CurrentBlockTime;

    private String TimeRemaining;

    public String getTimeElapsed() {
        return TimeElapsed;
    }

    public String getNextBlockTime() {
        return NextBlockTime;
    }

    public String getCurrentBlockNumber() {
        return Integer.toString(CurrentBlockNumber);
    }

    public String getCurrentBlockTime() {
        return CurrentBlockTime;
    }

    public String getTimeRemaining() {
        return TimeRemaining;
    }
}
