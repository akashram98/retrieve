package com.example.retrieve;

public class User {

    private String date;
    private String time;
    private String value;

    public User(){ }

    public User(String date, String time, String value) {
        this.date = date;
        this.time = time;
        this.value = value;
    }

    public String getdate() {
        return date;
    }
    public String gettime() {
        return time;
    }
    public String getvalue() {
        return value;
    }



}
