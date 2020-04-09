package com.example.eltonn;

import java.io.Serializable;

public class Gym implements Serializable {

    private int id;
    private String gymname;
    private String location;
    private String address;
    private String vacancies;
    private int stars;

    public Gym(int id, String gymname, String location, String address, String vacancies,int stars) {
        this.id = id;
        this.gymname = gymname;
        this.location = location;
        this.address = address;
        this.vacancies = vacancies;
        this.stars = stars;
    }

    public Gym(int stars, String location, String address, String vacancies, String gymname) {
        this.gymname = gymname;
        this.location = location;
        this.address = address;
        this.vacancies = vacancies;
        this.stars = stars;
    }

    public Gym() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVacancies() {
        return vacancies;
    }

    public void setVacancies(String vacancies) {
        this.vacancies = vacancies;
    }

    public String getGymname() {
        return gymname;
    }

    public void setGymname(String gymname) {
        this.gymname = gymname;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "id=" + id +
                ", gymname='" + gymname + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", vacancies='" + vacancies + '\'' +
                ", stars=" + stars +
                '}';
    }
}
