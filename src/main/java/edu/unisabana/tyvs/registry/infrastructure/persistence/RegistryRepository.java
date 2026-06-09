package edu.unisabana.tyvs.registry.infrastructure.persistence;

import edu.unisabana.tyvs.registry.application.port.out.RegistryRepositoryPort;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class RegistryRepository implements RegistryRepositoryPort {
    private final String jdbcUrl;
    public RegistryRepository(String jdbcUrl) { this.jdbcUrl = jdbcUrl; }
    public RegistryRepository() { this("jdbc:h2:mem:regdb;DB_CLOSE_DELAY=-1"); }
    @Override
    public void initSchema() throws Exception {
        try (Connection c = DriverManager.getConnection(jdbcUrl); Statement s = c.createStatement()) {
            s.execute("CREATE TABLE IF NOT EXISTS voters (id INT PRIMARY KEY, name VARCHAR(255), age INT, alive BOOLEAN)");
        }
    }
    @Override
    public boolean existsById(int id) throws Exception {
        try (Connection c = DriverManager.getConnection(jdbcUrl);
             PreparedStatement ps = c.prepareStatement("SELECT 1 FROM voters WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        }
    }
    @Override
    public void save(int id, String name, int age, boolean alive) throws Exception {
        try (Connection c = DriverManager.getConnection(jdbcUrl);
             PreparedStatement ps = c.prepareStatement("INSERT INTO voters (id, name, age, alive) VALUES (?, ?, ?, ?)")) {
            ps.setInt(1, id); ps.setString(2, name); ps.setInt(3, age); ps.setBoolean(4, alive);
            ps.executeUpdate();
        }
    }
    @Override
    public void deleteAll() throws Exception {
        try (Connection c = DriverManager.getConnection(jdbcUrl); Statement s = c.createStatement()) {
            s.execute("DELETE FROM voters");
        }
    }
}