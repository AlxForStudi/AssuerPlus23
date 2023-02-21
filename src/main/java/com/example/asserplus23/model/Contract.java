package com.example.asserplus23.model;

public class Contract {
    private String name;
    private String number;

    public Contract(){};
    public Contract(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "name='" + this.getName() + '\'' +
                ", number='" + this.getNumber() + '\'' +
                '}';
    }
}
