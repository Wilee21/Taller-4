package edu.unisabana.tyvs.registry.domain.model;

public class Person {
    private String name;
    private int id;
    private int age;
    private Gender gender;
    private boolean alive;

    public Person(String name, int id, int age, Gender gender, boolean alive) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.alive = alive;
    }
    public String getName() { return name; }
    public int getId() { return id; }
    public int getAge() { return age; }
    public Gender getGender() { return gender; }
    public boolean isAlive() { return alive; }
}