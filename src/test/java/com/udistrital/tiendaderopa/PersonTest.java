package com.udistrital.tiendaderopa;

import Modelo.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("Maria", "Perez", "maria@mail.com", "abcd");
    }

    @Test
    public void testLoginSuccess() {
        assertTrue(person.login("maria@mail.com", "abcd"));
        assertTrue(person.isLoggedIn());
    }

    @Test
    public void testLoginFailure() {
        assertFalse(person.login("maria@mail.com", "wrongpass"));
        assertFalse(person.isLoggedIn());
    }

    @Test
    public void testLogout() {
        person.login("maria@mail.com", "abcd");
        person.logout();
        assertFalse(person.isLoggedIn());
    }
}
