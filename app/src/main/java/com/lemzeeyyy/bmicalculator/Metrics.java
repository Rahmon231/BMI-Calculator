package com.lemzeeyyy.bmicalculator;

public class Metrics {
    private int weight;
    private int height;
    private int age;

    public Metrics() {
    }

    public Metrics(int weight, int height, int age) {
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
