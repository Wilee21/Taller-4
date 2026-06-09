package edu.unisabana.tyvs.registry.application.usecase;

import edu.unisabana.tyvs.registry.application.port.out.RegistryRepositoryPort;
import edu.unisabana.tyvs.registry.domain.model.Gender;
import edu.unisabana.tyvs.registry.domain.model.Person;
import edu.unisabana.tyvs.registry.domain.model.RegisterResult;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegistryWithMockTest {
    private RegistryRepositoryPort repo;
    private Registry registry;
    @Before
    public void setUp() {
        repo = mock(RegistryRepositoryPort.class);
        registry = new Registry(repo);
    }
    @Test
    public void shouldReturnDuplicatedWhenExists() throws Exception {
        when(repo.existsById(7)).thenReturn(true);
        Person p = new Person("Ana", 7, 25, Gender.FEMALE, true);
        assertEquals(RegisterResult.DUPLICATED, registry.registerVoter(p));
        verify(repo, never()).save(anyInt(), anyString(), anyInt(), anyBoolean());
    }
    @Test
    public void shouldSaveWhenNotExists() throws Exception {
        when(repo.existsById(8)).thenReturn(false);
        Person p = new Person("Luis", 8, 30, Gender.MALE, true);
        assertEquals(RegisterResult.VALID, registry.registerVoter(p));
        verify(repo, times(1)).save(8, "Luis", 30, true);
    }
    @Test(expected = IllegalStateException.class)
    public void shouldHandlePersistenceException() throws Exception {
        when(repo.existsById(9)).thenReturn(false);
        doThrow(new RuntimeException("DB error")).when(repo).save(anyInt(), anyString(), anyInt(), anyBoolean());
        Person p = new Person("Test", 9, 40, Gender.FEMALE, true);
        registry.registerVoter(p);
    }
}