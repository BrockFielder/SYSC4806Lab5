package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addressbooks")
public class AddressController {

    @Autowired
    private AddressBookRepository AddressRepo;

    @PostMapping
    public AddressBook createAddressBook(@RequestBody AddressBook book) {
        return AddressRepo.save(book);
    }

    @GetMapping("/{id}")
    public AddressBook getAddressBook(@PathVariable long id) {
        return AddressRepo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public AddressBook updateAddress(@PathVariable long id, @RequestBody AddressBook updated) {
        AddressBook existing = AddressRepo.findById(id).orElseThrow();
        existing.getBuddyList().clear();
        existing.getBuddyList().addAll(updated.getBuddyList());
        return AddressRepo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable long id) {
        AddressRepo.deleteById(id);
    }

    @PostMapping("/{id}/buddies")
    public BuddyInfo addBuddyToBook(@PathVariable Long id, @RequestBody BuddyInfo buddy) {
        AddressBook book = AddressRepo.findById(id).orElseThrow();
        buddy = new BuddyInfo(buddy.getName(), buddy.getNumber(), buddy.getHome());
        book.addBuddy(buddy);
        AddressRepo.save(book);
        return buddy;
    }
}
