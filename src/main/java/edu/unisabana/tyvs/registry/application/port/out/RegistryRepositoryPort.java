package edu.unisabana.tyvs.registry.application.port.out;

public interface RegistryRepositoryPort {
    void initSchema() throws Exception;
    boolean existsById(int id) throws Exception;
    void save(int id, String name, int age, boolean alive) throws Exception;
    void deleteAll() throws Exception;
}