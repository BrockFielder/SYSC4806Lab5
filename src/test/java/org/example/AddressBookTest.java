package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AddressBookTest {

    @Autowired
    private BuddyRepository buddyRepo;

    @Autowired
    private AddressBookRepository addressBookRepo;

    @Test
    public void testAddBuddy() {
        AddressBook book = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Alice", 905, "Port Hope", "40 10th Line St.");

        book.addBuddy(buddy);
        List<BuddyInfo> buddies = book.getBuddyList();

        assertEquals(1, buddies.size());
        assertEquals("Alice", buddies.get(0).getName());
        assertEquals(905, buddies.get(0).getNumber());
        assertEquals("Port Hope", buddies.get(0).getHome());
    }

    @Test
    public void testRemoveBuddy() {
        AddressBook book = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo("Alice", 613, "Cornwall","99 Victoria St." );

        BuddyInfo buddy2 = new BuddyInfo("Bob", 514, "Montreal", "16 Boulevard René-Lévesque");

        book.addBuddy(buddy1);
        book.addBuddy(buddy2);
        book.removeBuddy(buddy1);

        List<BuddyInfo> buddies = book.getBuddyList();
        assertEquals(1, buddies.size());
        assertEquals("Bob", buddies.get(0).getName());
    }

    @Test
    public void testPersistence() {
        // Clear previous test data
        addressBookRepo.deleteAll();
        buddyRepo.deleteAll();

        // Create some BuddyInfo objects
        BuddyInfo buddy1 = new BuddyInfo("Alice", 42, "Ottawa, ON", "957 Bank St,");
        BuddyInfo buddy2 = new BuddyInfo("John", 40, "Perth, ON", "Main St.");
        BuddyInfo buddy3 = new BuddyInfo("Peter", 42, "Maniwaki, PQ", "56 Rue Des Oblats");

        // Add them to an AddressBook
        AddressBook book = new AddressBook();
        book.addBuddy(buddy1);
        book.addBuddy(buddy2);
        book.addBuddy(buddy3);
        AddressBook savedBook = addressBookRepo.save(book);

        assertNotNull(savedBook.getId(), "Saved AddressBook should have an ID assigned");
        assertEquals(3, savedBook.getBuddyList().size());

        AddressBook found = addressBookRepo.findById(savedBook.getId()).orElse(null);
        assertNotNull(found, "AddressBook should be retrievable from database");
        assertEquals(3, found.getBuddyList().size(), "Buddy list should persist correctly");

        found.getBuddyList().forEach(b -> assertNotNull(b.getId(), "Each Buddy should have an ID"));
    }
}
