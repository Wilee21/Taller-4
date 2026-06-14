package edu.unisabana.tyvs.registry.application.usecase;

import edu.unisabana.tyvs.registry.application.port.out.RegistryRepositoryPort;
import edu.unisabana.tyvs.registry.domain.model.Gender;
import edu.unisabana.tyvs.registry.domain.model.Person;
import edu.unisabana.tyvs.registry.domain.model.RegisterResult;
import edu.unisabana.tyvs.registry.infrastructure.persistence.RegistryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistryTest {

    private RegistryRepositoryPort repo;
    private Registry registry;

    @BeforeEach
    public void setUp() throws Exception {
        repo = new RegistryRepository("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        repo.initSchema();
        repo.deleteAll();
        registry = new Registry(repo);
    }

    @Test
    public void validPerson() throws Exception {
        Person p = new Person("Ana", 100, 30, Gender.FEMALE, true);

        assertEquals(RegisterResult.VALID, registry.registerVoter(p));
        assertTrue(repo.existsById(100));
    }

    @Test
    public void duplicate() throws Exception {
        Person p1 = new Person("Ana", 100, 30, Gender.FEMALE, true);
        Person p2 = new Person("Juan", 100, 25, Gender.MALE, true);

        registry.registerVoter(p1);

        assertEquals(RegisterResult.DUPLICATED, registry.registerVoter(p2));
    }

    @Test
    public void underage() {
        Person p = new Person("Niño", 101, 16, Gender.MALE, true);

        assertEquals(RegisterResult.UNDERAGE, registry.registerVoter(p));
    }

    @Test
    public void dead() {
        Person p = new Person("Fallecido", 102, 45, Gender.FEMALE, false);

        assertEquals(RegisterResult.DEAD, registry.registerVoter(p));
    }

    @Test
    public void negativeAge() {
        Person p = new Person("Invalido", 103, -1, Gender.MALE, true);

        assertEquals(RegisterResult.INVALID_AGE, registry.registerVoter(p));
    }

    @Test
    public void invalidId() {
        Person p = new Person("Sin ID", -5, 25, Gender.FEMALE, true);

        assertEquals(RegisterResult.INVALID, registry.registerVoter(p));
    }
}