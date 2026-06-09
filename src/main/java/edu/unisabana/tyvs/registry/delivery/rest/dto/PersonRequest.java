package edu.unisabana.tyvs.registry.delivery.rest.dto;
import javax.validation.constraints.*;
public class PersonRequest {
    @NotBlank private String name;
    @Positive private int id;
    @Min(0) @Max(150) private int age;
    @NotNull private String gender;
    @NotNull private Boolean alive;
    // getters y setters (omitidos por brevedad, añádelos)
}