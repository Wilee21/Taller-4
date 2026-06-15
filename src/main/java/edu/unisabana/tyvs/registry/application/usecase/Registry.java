package edu.unisabana.tyvs.registry.application.usecase;

import edu.unisabana.tyvs.registry.application.port.out.RegistryRepositoryPort;
import edu.unisabana.tyvs.registry.domain.model.Person;
import edu.unisabana.tyvs.registry.domain.model.RegisterResult;
import org.springframework.stereotype.Service;

@Service
public class Registry {
    private final RegistryRepositoryPort repo;
    public Registry(RegistryRepositoryPort repo) { this.repo = repo; }
    public RegisterResult registerVoter(Person p) {
        if (p == null || p.getId() <= 0) return RegisterResult.INVALID;
        if (p.getAge() < 0) return RegisterResult.INVALID_AGE;
        if (!p.isAlive()) return RegisterResult.DEAD;
        if (p.getAge() < 18) return RegisterResult.UNDERAGE;
        try {
            if (repo.existsById(p.getId())) return RegisterResult.DUPLICATED;
            repo.save(p.getId(), p.getName(), p.getAge(), p.isAlive());
            return RegisterResult.VALID;
        } catch (Exception e) {
            throw new IllegalStateException("Error de persistencia", e);
        }
    }
}