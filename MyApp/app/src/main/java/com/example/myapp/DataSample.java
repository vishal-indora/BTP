package com.example.myapp;

public class DataSample {
    public static String time, frequency, dc, sg, ag;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public String getAg() {
        return ag;
    }

    public void setAg(String ag) {
        this.ag = ag;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    @Override
    public String toString() {
        return "DataSample{" +
                "time='" + time + '\'' +
                ", frequency='" + frequency + '\'' +
                ", dc='" + dc + '\'' +
                ", sg='" + sg + '\'' +
                ", ag='" + ag + '\'' +
                '}';
    }
}
