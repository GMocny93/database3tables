package com.example.grzesiek.database3tables;


public class weight {

    int _id;
    int _year;
    int _month;
    int _day;
    double _weight;


    public weight() {
    }

    public weight(int id, int year, int month, int day, double weight){
        this._id = id;
        this._year = year;
        this._month = month;
        this._day = day;
        this._weight = weight;
    }
    public weight(int year, int month, int day, double weight){
        this._year = year;
        this._month = month;
        this._day = day;
        this._weight = weight;
    }

    //GETTERS SETTERS

    public int getId() {
        return this._id;
    }
    public void setId(int id) {
        this._id = id;
    }

    //year
    public int getYear() {
        return this._year;
    }

    public void setYear(int year) {
        this._year = year;
    }

    //month
    public int getMonth() {
        return this._month;
    }

    public void setMonth(int month) {
        this._month = month;
    }

    //day
    public int getDay() {
        return this._day;
    }

    public void setDay(int day) {
        this._day = day;
    }

    //WGweight
    public double getWeight() {
        return this._weight;
    }

    public void setWeight(double weight) {
        this._weight = weight;
    }

}