package edu.unisabana.tyvs.registry.delivery.rest;

import edu.unisabana.tyvs.registry.application.usecase.Registry;
import edu.unisabana.tyvs.registry.domain.model.Gender;
import edu.unisabana.tyvs.registry.domain.model.Person;
import edu.unisabana.tyvs.registry.delivery.rest.dto.PersonRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class RegistryController {
    private final Registry registry;
    public RegistryController(Registry registry) { this.registry = registry; }
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody PersonRequest request) {
        try {
            Gender gender = Gender.valueOf(request.getGender().toUpperCase());
            Person person = new Person(request.getName(), request.getId(), request.getAge(), gender, request.getAlive());
            return ResponseEntity.ok(registry.registerVoter(person).name());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid gender value");
        }
    }
}