package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class ViewController {
    @Autowired
    private AddressBookRepository AddressRepo;

    @GetMapping("/")
    public String home() {
        // Create a new AddressBook if none exist
        if (AddressRepo.count() == 0) {
            AddressBook newBook = new AddressBook();
            AddressRepo.save(newBook);
        }
        AddressBook firstBook = AddressRepo.findAll().iterator().next();
        return "redirect:/addressbook/" + firstBook.getId();
    }


    @GetMapping("/addressbook/{id}")
    public String viewAddressBook(@PathVariable Long id, Model model) {


        AddressBook book = AddressRepo.findById(id).orElse(null);
        if (book == null) {

            model.addAttribute("bookId", id);
            model.addAttribute("buddies", java.util.List.of());
            return "addressbook";}

        model.addAttribute("bookId", id);
        model.addAttribute("buddies", book != null ? book.getBuddyList() : java.util.List.of());
        return "addressbook";
    }
}

