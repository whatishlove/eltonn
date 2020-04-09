package com.example.eltonn;
import java.io.Serializable;

public class WR implements Serializable {

    private int id;
    private String wrname, duration, url;

    public WR(int id, String wrname, String duration, String url) {
        this.id = id;
        this.wrname = wrname;
        this.duration = duration;
        this.url = url;
    }
    public WR(String wrname, String duration, String url) {
        this.wrname = wrname;
        this.duration = duration;
        this.url = url;
    }
    public WR() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getWrname() {
        return wrname;
    }
    public void setWrname(String wrname) {
        this.wrname = wrname;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WR{" +
                "id=" + id +
                ", wrname='" + wrname + '\'' +
                ", duration='" + duration + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
