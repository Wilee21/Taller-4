package edu.unisabana.tyvs.registry.delivery.rest.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class PersonRequest {

    @NotBlank
    private String name;

    @Positive
    private int id;

    @Min(0)
    @Max(150)
    private int age;

    @NotNull
    private String gender;

    @NotNull
    private Boolean alive;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }
}