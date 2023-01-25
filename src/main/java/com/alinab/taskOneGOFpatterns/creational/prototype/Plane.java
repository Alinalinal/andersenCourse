package com.alinab.taskOneGOFpatterns.creational.prototype;

public class Plane implements Copyable {

    private String model;
    private int yearOfProduction;

    public Plane(String model, int yearOfProduction) {
        this.model = model;
        this.yearOfProduction = yearOfProduction;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public Plane copy() {
        return new Plane(this.model, this.yearOfProduction);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "model='" + model + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}
