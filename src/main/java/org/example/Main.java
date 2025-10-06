import org.example.AddressBook;
import org.example.BuddyInfo;

public class Main {
    public static void main(String[] args) {
        AddressBook book = new AddressBook();

        BuddyInfo buddy1 = new BuddyInfo("Alice",905, "Oshawa");
        BuddyInfo buddy2 = new BuddyInfo("Bob", 810, "Hull");
        BuddyInfo buddy3 = new BuddyInfo("Charlie", 613, "Smiths Falls");

        book.addBuddy(buddy1);
        book.addBuddy(buddy2);
        book.addBuddy(buddy3);

        System.out.println("Buddies in the AddressBook:");
        for (BuddyInfo b : book.getBuddyList()) {
            System.out.println(b.getName());
        }
    }
}
