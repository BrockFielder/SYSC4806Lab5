package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.BuddyInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BuddyInfoTest {

    @Autowired
    private BuddyRepository buddyRepository;

    @Test
    public void testBuddyAddressField() {
        BuddyInfo buddy = new BuddyInfo("Alice", 905, "Oshawa", "123 King St E");

        assertEquals("Alice", buddy.getName());
        assertEquals(905, buddy.getNumber());
        assertEquals("Oshawa", buddy.getHome());
        assertEquals("123 King St E", buddy.getAddress());
    }

    @Test
    public void testConstructorStoresName() {
        BuddyInfo buddy = new BuddyInfo("Charlie", 8, "Aylmer", "Main St");
        assertNotNull(buddy);
        assertEquals("Charlie", buddy.getName());
    }

    @Test
    public void testGetName() {
        BuddyInfo buddy = new BuddyInfo("Diana", 61, "Perth", "Main St");
        assertEquals("Diana", buddy.getName());
    }

    @Test
    public void persist() {
        BuddyInfo buddy = new BuddyInfo("Alice", 42, "Ottawa", "140 Sunnyside Ave");

        BuddyInfo savedBuddy = buddyRepository.save(buddy);

        BuddyInfo found = buddyRepository.findById(savedBuddy.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Alice", found.getName());
        assertEquals(42, found.getNumber());
        assertEquals("Ottawa", found.getHome());
    }

}
