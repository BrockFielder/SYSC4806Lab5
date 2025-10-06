
package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "addressbook_id")
    private List<BuddyInfo> buddyList;

    public AddressBook() {
        buddyList = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo buddy){
        buddyList.add(buddy);
    }

    public List<BuddyInfo> getBuddyList() {
        return buddyList;
    }

    public void removeBuddy (BuddyInfo buddy){
        buddyList.remove(buddy);
    }

    public long getId() { return id; }

}
